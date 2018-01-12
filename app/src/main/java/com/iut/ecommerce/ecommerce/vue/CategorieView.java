package com.iut.ecommerce.ecommerce.vue;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.iut.ecommerce.ecommerce.R;
import com.iut.ecommerce.ecommerce.dao.CategorieAdaptateur;
import com.iut.ecommerce.ecommerce.modele.Categorie;

import java.util.ArrayList;

/**
 * Created by Damien on 09/01/2018.
 */

public class CategorieView extends Fragment {

    private ArrayList<Categorie> liste;

    public CategorieView() {
        super();
    }

    @Override
    public void onStart() {
        super.onStart();

        CategorieAdaptateur adaptateur = new CategorieAdaptateur(this.getContext(), liste);
        ListView listView = getActivity().findViewById(R.id.maListView);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Categories");

        liste = new ArrayList<Categorie>();
        liste.add(new Categorie(1,"Casquette", "casquette.png"));
        liste.add(new Categorie(2,"Pantalon", "pantalon.png"));
        liste.add(new Categorie(3,"Tee-shirt", "teeshirt.png"));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.categorie_main, container, false);
    }

}
