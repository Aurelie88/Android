package com.iut.ecommerce.ecommerce.listener;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;

import com.iut.ecommerce.ecommerce.adaptateur.CategorieAdaptateur;
import com.iut.ecommerce.ecommerce.dao.CategorieDao;
import com.iut.ecommerce.ecommerce.fragment.CategorieView;
import com.iut.ecommerce.ecommerce.modele.Categorie;

import static com.iut.ecommerce.ecommerce.Message.supprimerAlertDialog;

/**
 * Created by Damien on 04/02/2018.
 */

public class DeleteCategorieListener implements DialogInterface.OnClickListener, View.OnClickListener{

    private Categorie categorie;
    private Context context;
    private int position;
    private CategorieAdaptateur categorieAdaptateur;

    public DeleteCategorieListener(Categorie categorie, Context context, CategorieAdaptateur categorieAdaptateur) {
        this.categorie = categorie;
        this.context = context;
        this.categorieAdaptateur = categorieAdaptateur;
    }

    @Override
    public void onClick(View view) {
        this.position = (int) view.getTag();
        // On affiche la boite de dialogue pour la suppression du message
        supprimerAlertDialog(this.context,"Supprimer", "Voulez-vous réellement supprimer l'élément sélectionné : ", this.categorie, this);

    }


    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        // On passe à l'unique instance de CategorieDao l'unique instance de CategorieView
        // Il n'existe pour chaque qu'un objet DAO et View!!!
        CategorieView categorieView = CategorieView.getInstance();
        // On fait ici un appel à la base de données pour supprimer la catégorie
        CategorieDao.getInstance(categorieView).delete(categorie);
        // On passe en paramètre la catégorie à supprimer pour la categorieView
        // On fait ici une suppression dans l'adpateur;
        Log.i("_cat", categorie.getNomCateg());
        Log.i("_cat", String.valueOf(position));
        Log.i("adapDCL", this.categorieAdaptateur+"");
        Log.i("listeDCL_avantRemove", this.categorieAdaptateur.liste.size() + "");

        this.categorieAdaptateur.liste.remove(position);
        Log.i("listeDCL_apresRemove", this.categorieAdaptateur.liste.size() + "");
        this.categorieAdaptateur.notifyDataSetChanged();
        // On renvoie l'information que la suppression est terminée
        CategorieView.getInstance().notifyRetourRequete("supprimer");

    }
}
