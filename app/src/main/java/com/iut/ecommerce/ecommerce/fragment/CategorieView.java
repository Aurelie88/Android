package com.iut.ecommerce.ecommerce.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.iut.ecommerce.ecommerce.BoutiqueActivity;
import com.iut.ecommerce.ecommerce.Message;
import com.iut.ecommerce.ecommerce.R;
import com.iut.ecommerce.ecommerce.adaptateur.CategorieAdaptateur;
import com.iut.ecommerce.ecommerce.dao.ArticleDao;
import com.iut.ecommerce.ecommerce.dao.CategorieDao;
import com.iut.ecommerce.ecommerce.modele.Categorie;
import com.iut.ecommerce.ecommerce.utils.ActiviteEnAttenteAvecResultat;

import java.util.ArrayList;

import static com.iut.ecommerce.ecommerce.BoutiqueActivity.boutiqueActivity;
import static com.iut.ecommerce.ecommerce.BoutiqueActivity.getAppContext;

/**
 * Created by Damien on 09/01/2018.
 */

public class CategorieView extends Fragment implements ActiviteEnAttenteAvecResultat {

    public ArrayList<Categorie> liste = new ArrayList<Categorie>();
    private CategorieAdaptateur adaptateur;
    private ListView listView;
    private Categorie categorie;
    private Categorie filteredCategorie;

    private static CategorieView categorieView;

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

        setFilteredCategorie(null);

        // Définition du nom de l'activité
        getActivity().setTitle("Boutique");

        // Définition de la listView
        this.listView = getActivity().findViewById(R.id.categListView);

        // Récupération des éléments de la liste
        CategorieDao.getInstance(this).findAll();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // On reset la catégorie sélectionnée
            setFilteredCategorie(null);
        } else {
            // Faire autre chose...
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.categorie_main, container, false);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void notifyRetourRequete(String resultat) {
        // Après création/modification/suppression, ajout d'éventuel message
        if ("supprimer".equals(resultat)) {
            Log.i("_S", "supprimer");

        } else if ("modifier".equals(resultat)) {
            Log.i("_M", "modifier");
            boutiqueActivity.setCurrentFragment();

        } else if ("creer".equals(resultat)){
            Log.i("_C", "creer");
            boutiqueActivity.setCurrentFragment();

        } else if ("nok".equals("nok")) {
            Log.i("_S", "erreur surpression");

        } else {
            Log.i("_S", "autre erreur");
        }
    }


    @Override
    public void notifyRetourRequeteFindAll(ArrayList liste) {
        this.liste.clear();
        this.liste.addAll(liste);

        // Définition de l'adaptateur
        this.adaptateur = new CategorieAdaptateur(getContext(), liste);
        // Lien entre adaptateur et listview (remplissage de la liste
        listView.setAdapter(adaptateur);
        // Définition de l'action sur click sur un élément de la liste (texte ou bitmapArrayList catégorie)
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO : Action à réaliser lors d'un clique sur la liste ?
                Categorie item = (Categorie) adapterView.getItemAtPosition(i);
                Log.i("_cv", "Clique sur une catégorie");
                setFilteredCategorie(item);
                Log.i("_cv", getFilteredCategorie().getNomCateg());
                boutiqueActivity.viewPager.setCurrentItem(1);
            }
        });
        ((BaseAdapter) this.listView.getAdapter()).notifyDataSetChanged();
    }


    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Categorie getFilteredCategorie() {
        return filteredCategorie;
    }

    public void setFilteredCategorie(Categorie filteredCategorie) {
        this.filteredCategorie = filteredCategorie;
    }
}