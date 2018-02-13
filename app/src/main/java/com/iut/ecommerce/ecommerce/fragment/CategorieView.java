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
import android.widget.ListView;

import com.iut.ecommerce.ecommerce.R;
import com.iut.ecommerce.ecommerce.utils.ActiviteEnAttenteAvecResultat;
import com.iut.ecommerce.ecommerce.adaptateur.CategorieAdaptateur;
import com.iut.ecommerce.ecommerce.dao.CategorieDao;
import com.iut.ecommerce.ecommerce.modele.Categorie;
import com.squareup.picasso.*;

import java.util.ArrayList;

/**
 * Created by Damien on 09/01/2018.
 */

public class CategorieView extends Fragment implements ActiviteEnAttenteAvecResultat,  AdapterView.OnItemClickListener, Dialog.OnClickListener {

    private ArrayList<Categorie> liste;
    private CategorieAdaptateur adaptateur;
    private ListView listView;
    private Categorie categorie;

    private static CategorieView categorieView = null;


    public static CategorieView getInstance(){
        if (categorieView==null){
            categorieView = new CategorieView();
        }
        return categorieView;
    }

    public CategorieView() {

    }

    @Override
    public void onStart() {
        super.onStart();

        // Définition du nom de l'activité
        getActivity().setTitle("Boutique");

        liste = new ArrayList<Categorie>();

        // Définition de la listView
        this.listView = getActivity().findViewById(R.id.categListView);

        // Définition de l'adaptateur
        this.adaptateur = new CategorieAdaptateur(getActivity(), liste);
        // Lien entre adaptateur et listview (remplissage de la liste
        listView.setAdapter(adaptateur);
        // Définition de l'action sur click sur un élément de la liste (texte ou bitmapArrayList catégorie)
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO : Action à réaliser lors d'un clique sur la liste ?
            }
        });

        // Récupération des éléments de la liste
        CategorieDao.getInstance(this).findAll();

        notifyRetourRequeteFindAll(liste);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if (getView() != null) {
            return getView();
        }else{
            return inflater.inflate(R.layout.categorie_main, container, false);
        }

    }

    @Override
    public void notifyRetourRequete(String resultat) {
        // Après création/modification/suppression, on remet la liste à jour
        // en effectuant un findAll()
        CategorieDao.getInstance(this).findAll();

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
        setCategorie(this.liste.get(position));
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}