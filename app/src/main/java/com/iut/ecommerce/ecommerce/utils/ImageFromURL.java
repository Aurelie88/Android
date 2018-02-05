package com.iut.ecommerce.ecommerce.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Damien on 24/01/2018.
 */

public class ImageFromURL extends AsyncTask<String, Void, Drawable> {

    private ImageView bmImage;
    private Context context;
    private ArrayList<Drawable> imageList = new ArrayList<>();

    public ImageFromURL(Context context, ImageView bmImage) {
        this.bmImage = bmImage;
        this.context = context;
    }

    public ImageFromURL(ArrayList<Drawable> bmImage) {
        this.imageList = bmImage;
    }

    @Override
    protected Drawable doInBackground(String... urls) {

        String urlIcone = urls[0];
        Bitmap icone = null;

        try {
            InputStream in = new java.net.URL(urlIcone).openStream();
            icone = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.i("Pas d'icone", "Aucune icone trouvée sur le serveur");
        }
        return new BitmapDrawable(context.getResources(),icone);
    }

    @Override
    protected void onPostExecute(Drawable result) {
        if (result==null) {
              Log.i("Pas d'icone", "Echec chargement");

              // Afficher une image par défaut si echec du chargement
            try {
                InputStream inputstream=context.getAssets().open("croix.png");
                Drawable drawable = Drawable.createFromStream(inputstream, "croix.png");
                //bmImage.setImageDrawable(drawable);
                imageList.add(drawable);
            } catch (IOException e) {
                Log.i("imageview", e.getMessage());
            }

        } else {
            //bmImage.setImageBitmap(result);
            imageList.add(result);
        }
        //this.activite.terminePatience();
    }

}
