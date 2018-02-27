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

    public DeleteCategorieListener(Categorie categorie, Context context) {
        this.categorie = categorie;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        CategorieView.getInstance().setPosition((int) view.getTag());
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
    }
}
