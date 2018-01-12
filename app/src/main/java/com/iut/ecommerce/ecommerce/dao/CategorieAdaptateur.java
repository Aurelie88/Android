package com.iut.ecommerce.ecommerce.dao;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.iut.ecommerce.ecommerce.R;
import com.iut.ecommerce.ecommerce.modele.Categorie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damien on 12/01/2018.
 */

public class CategorieAdaptateur extends ArrayAdapter<Categorie>{

    private Context context;

    public CategorieAdaptateur(Context context, ArrayList<Categorie> liste){
        super(context, 0, (ArrayList<Categorie>) liste);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Categorie uneCategorie = getItem(position);

        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_list_categorie, parent, false);
        }

        return super.getView(position, convertView, parent);
    }
}
