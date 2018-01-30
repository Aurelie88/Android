package com.iut.ecommerce.ecommerce.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.iut.ecommerce.ecommerce.R;
import com.iut.ecommerce.ecommerce.adaptateur.CategorieAdaptateur;
import com.iut.ecommerce.ecommerce.modele.Categorie;

import java.util.ArrayList;

/**
 * Created by Damien on 09/01/2018.
 */

public class ArticleView extends Fragment {

    private ListView maListe;
    private ListAdapter adaptateur;

    public ArticleView() {
        super();
    }


    private ArrayList liste;

    @Override
    public void onStart() {
        super.onStart();

        getActivity().setTitle("Boutique");

        liste = new ArrayList<Categorie>();
        liste.add(new Categorie(1, "Casquette", "casquette.png"));
        liste.add(new Categorie(2, "Pantalon", "pantalon.png"));
        liste.add(new Categorie(3, "Tee-shirt", "teeshirt.png"));

        this.adaptateur = new CategorieAdaptateur(this.getContext(), liste);
        ListView listView = getActivity().findViewById(R.id.articleListView);
        listView.setAdapter(adaptateur);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.article_main, container, false);
    }
}
