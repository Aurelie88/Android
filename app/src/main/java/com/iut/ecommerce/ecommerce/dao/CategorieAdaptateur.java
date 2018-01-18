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

import com.iut.ecommerce.ecommerce.R;
import com.iut.ecommerce.ecommerce.modele.Categorie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damien on 12/01/2018.
 */

public class CategorieAdaptateur extends ArrayAdapter<Categorie>{

    private List<Categorie> data;
    private int listItemResLayout;
    private Context context;

    public CategorieAdaptateur(Context context, ArrayList<Categorie> liste){
        super(context, 0, liste);

        this.data = data;
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Categorie uneCategorie = getItem(position);

        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_list_categorie, parent, false);
        }

        TextView tvNom = convertView.findViewById(R.id.cl_nom);
        tvNom.setText(uneCategorie.getNomCateg());

        ImageView icone = (ImageView) convertView.findViewById(R.id.cl_visuel);
        if (icone.getDrawable() == null) {

            try {
                    icone.setImageDrawable(Drawable.createFromStream(context.getAssets().open(uneCategorie.getVisuelCateg()), uneCategorie.getNomCateg()));

            } catch (IOException e) {
                Log.i("imageview", e.getMessage());
            }

        }




        ImageView icone2 = (ImageView) convertView.findViewById(R.id.cl_modifier);
        if (icone.getDrawable() == null) {

            try {

                icone.setImageDrawable(Drawable.createFromStream(context.getAssets().open("crayon.png"), "Modifier"));

            } catch (IOException e) {
                Log.i("imageview", e.getMessage());
            }

        }

        ImageView icone3 = (ImageView) convertView.findViewById(R.id.cl_supprimer);
        if (icone.getDrawable() == null) {

            try {

                icone.setImageDrawable(Drawable.createFromStream(context.getAssets().open("corbeille.png"), "Effacer"));

            } catch (IOException e) {
                Log.i("imageview", e.getMessage());
            }

        }
        return convertView;
        //return super.getView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
