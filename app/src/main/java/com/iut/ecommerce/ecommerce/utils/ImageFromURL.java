package com.iut.ecommerce.ecommerce.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.iut.ecommerce.ecommerce.BoutiqueActivity;
import com.iut.ecommerce.ecommerce.adaptateur.CategorieAdaptateur;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Damien on 24/01/2018.
 */
//*****************************************************//
// Pas utile si utilisation de la librairie Picasso
//*****************************************************//

public class ImageFromURL extends AsyncTask<String, Void, Bitmap> {

    private ImageView bmImage;
    private Context context;
    private ArrayList<Bitmap> imageList = new ArrayList<>();

    public ImageFromURL(ArrayList<Bitmap> bmImage) {
        this.imageList = bmImage;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {

        String urlIcone = urls[0];
        Bitmap icone = null;

        try {
            InputStream in = new java.net.URL(urlIcone).openStream();
            icone = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.i("iLc", "Thread - (Clause catch) Aucune icone trouvée sur le serveur");
        }
        return icone;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        if (result==null) {
            Log.i("iLd", "onPostExecute - Aucune icone trouvée sur le serveur");

              // Afficher une bitmapArrayList par défaut si echec du chargement
            try {
                Log.i("iLe", "On tente de récupérer l'image par défaut");
                InputStream inputstream= BoutiqueActivity.getAppContext().getAssets().open("croix.png");
                Bitmap icone = BitmapFactory.decodeStream(inputstream);
                //CategorieAdaptateur.bitmapArrayList.add(icone);
                //Log.i("iLf", "ImageFromURL => longueur liste Bitmap : "+ CategorieAdaptateur.bitmapArrayList.size());
            } catch (IOException e) {
                Log.i("iLg", e.getMessage());
            }
        }
        //CategorieAdaptateur.bitmapArrayList.add(result);
        //Log.i("iLh", "ImageFromURL => longueur liste Bitmap : "+ CategorieAdaptateur.bitmapArrayList.size());


        //this.activite.terminePatience();
    }


}
