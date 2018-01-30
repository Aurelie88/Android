package com.iut.ecommerce.ecommerce.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.iut.ecommerce.ecommerce.R;
import com.iut.ecommerce.ecommerce.adaptateur.CategorieAdaptateur;
import com.iut.ecommerce.ecommerce.modele.Categorie;

import java.util.ArrayList;


public class PromotionView extends Fragment {

    private ArrayList liste;
    private CategorieAdaptateur adaptateur;

    public PromotionView() {
        super();
    }


    @Override
    public void onStart() {
        super.onStart();

        getActivity().setTitle("Boutique");

        liste = new ArrayList<Categorie>();
        liste.add(new Categorie(1,"50%", "casquette.png"));
        liste.add(new Categorie(2,"40%", "pantalon.png"));

        this.adaptateur = new CategorieAdaptateur(this.getContext(), liste);
        ListView listView = getActivity().findViewById(R.id.promoListView);
        listView.setAdapter(adaptateur);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.promotion_main, container, false);
    }
}
