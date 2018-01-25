package com.iut.ecommerce.ecommerce.dao;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iut.ecommerce.ecommerce.ImageFromURL;
import com.iut.ecommerce.ecommerce.R;
import com.iut.ecommerce.ecommerce.modele.Categorie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damien on 12/01/2018.
 */

public class CategorieAdaptateur extends ArrayAdapter<Categorie>{

    private Context context;

    public CategorieAdaptateur(Context context, ArrayList<Categorie> liste){
        super(context, 0, liste);
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Categorie uneCategorie = getItem(position);

        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_list_categorie, parent, false);
        }

        // Mise à jour du nom sur la liste
        TextView tvNom = convertView.findViewById(R.id.cl_nom);
        tvNom.setText(uneCategorie.getNomCateg());


        ImageView icone = (ImageView) convertView.findViewById(R.id.cl_visuel);
        if (icone.getDrawable() == null) {

            ImageFromURL ifu = new ImageFromURL(getContext(), icone);
            //ifu.execute("https://infodb.iutmetz.univ-lorraine.fr/~laroche5/ppo/ecommerce/cintre.png");
            ifu.execute("https://infodb.iutmetz.univ-lorraine.fr/~laroche5/ppo/ecommerce/" + uneCategorie.getVisuelCateg());



        }

        return convertView;
        //return super.getView(position, convertView, parent);
    }
}
