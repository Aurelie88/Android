package com.iut.ecommerce.ecommerce.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.iut.ecommerce.ecommerce.R;
import com.iut.ecommerce.ecommerce.utils.ActiviteEnAttenteAvecResultat;
import com.iut.ecommerce.ecommerce.adaptateur.CategorieAdaptateur;
import com.iut.ecommerce.ecommerce.dao.CategorieDao;
import com.iut.ecommerce.ecommerce.modele.Categorie;

import java.util.ArrayList;

/**
 * Created by Damien on 09/01/2018.
 */

public class CategorieView extends Fragment implements ActiviteEnAttenteAvecResultat,  AdapterView.OnItemClickListener, Dialog.OnClickListener {

    private ArrayList<Categorie> liste;
    private CategorieAdaptateur adaptateur;
    private ListView listView;


    public CategorieView() {

    }

   @Override
    public void onStart() {
        super.onStart();

       getActivity().setTitle("Boutique");

       liste = new ArrayList<Categorie>();

/*liste.add(new Categorie(1,"Casquettes", "casquettes.png"));
        liste.add(new Categorie(2,"Chemises", "chemises.png"));
        liste.add(new Categorie(3,"Bonnets", "bonnets.png"));*/

        //this.progressBar = getActivity().findViewById(R.id.cf_patience);
        //this.listView = getActivity().findViewById(R.id.categListView);

        CategorieDao.getInstance(this).findAll();



        this.adaptateur = new CategorieAdaptateur(this.getContext(), liste);
        this.listView = getActivity().findViewById(R.id.categListView);
        this.listView.setOnItemClickListener(this);
        listView.setAdapter(adaptateur);
        notifyRetourRequeteFindAll(liste);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.categorie_main, container, false);
    }

    @Override
    public void notifyRetourRequete(String resultat) {

    }

    @Override
    public void notifyRetourRequeteFindAll(ArrayList liste) {
        this.liste.clear();
        this.liste.addAll(liste);

        ((BaseAdapter) this.listView.getAdapter()).notifyDataSetChanged();
        //this.terminePatience();

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("adapter", String.valueOf(id));
    }

}