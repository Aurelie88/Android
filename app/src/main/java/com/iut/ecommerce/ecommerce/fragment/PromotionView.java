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
import com.iut.ecommerce.ecommerce.adaptateur.PromotionAdaptateur;
import com.iut.ecommerce.ecommerce.dao.PromotionDao;
import com.iut.ecommerce.ecommerce.modele.Promotion;
import com.iut.ecommerce.ecommerce.utils.ActiviteEnAttenteAvecResultat;

import java.util.ArrayList;


public class PromotionView extends Fragment implements ActiviteEnAttenteAvecResultat,  AdapterView.OnItemClickListener, Dialog.OnClickListener{

    public ArrayList<Promotion> liste = new ArrayList<Promotion>();
    private PromotionAdaptateur adaptateur;
    private ListView listView;
    private Promotion promotion;

    private static PromotionView promotionView;

    public static PromotionView getInstance(){
        if (promotionView==null){
            promotionView = new PromotionView();
        }
        return promotionView;
    }

    public PromotionView() {

    }

    @Override
    public void onStart() {
        super.onStart();

        // Définition du nom de l'activité
        getActivity().setTitle("Boutique");

        // Définition de la listView
        this.listView = getActivity().findViewById(R.id.promoListView);

        // On supprime les promos éventuellement dépassées
        PromotionDao.getInstance(this).check();

        // Récupération des éléments de la liste
        PromotionDao.getInstance(this).findAll();
        Log.i("_L", this.liste.toString());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.promotion_main, container, false);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void notifyRetourRequete(String resultat) {
        // Après création/modification/suppression, on remet la liste à jour
        if ("supprimer".equals(resultat)) {
            Log.i("_S", "Supprimer");

        } else if ("modifier".equals(resultat)) {
            Log.i("_M", "Modifier");
        } else {
            Log.i("_C", "Création");
        }
    }


    @Override
    public void notifyRetourRequeteFindAll(ArrayList liste) {

        this.liste.clear();
        this.liste.addAll(liste);

        // Définition de l'adaptateur
        this.adaptateur = new PromotionAdaptateur(getContext(), liste);
        // Lien entre adaptateur et listview (remplissage de la liste
        listView.setAdapter(adaptateur);
        // Définition de l'action sur click sur un élément de la liste (texte ou bitmapArrayList catégorie)
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO : Action à réaliser lors d'un clique sur la liste ?
            }
        });

        ((BaseAdapter) this.listView.getAdapter()).notifyDataSetChanged();

    }
    //this.terminePatience();

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("adapter", String.valueOf(id));
        setPromotion(this.liste.get(position));
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
}
