package com.iut.ecommerce.ecommerce.vue;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.iut.ecommerce.ecommerce.R;

/**
 * Created by Damien on 09/01/2018.
 */

public class ArticleView extends Fragment {

    private ListView maListe;
    private ListAdapter adaptateur;

    public ArticleView() {
        super();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Article");


        // ListView
            this.maListe = maListe.findViewById(R.id.maListView);
            // Définition des écouteurs sur la liste
            this.maListe.setOnItemClickListener((AdapterView.OnItemClickListener) this);
            this.maListe.setOnItemLongClickListener((AdapterView.OnItemLongClickListener) this);

            // Adaptateur Portfeuille<Devise> -> ListView
            //this.adaptateur = new ArrayAdapter<>(
            //        this, android.R.layout.simple_list_item_1, this.monPorteFeuille.getValeurs());
    }
            //this.maListe.setAdapter(this.adaptateur);
    //}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.article_main, container, false);
    }
}
