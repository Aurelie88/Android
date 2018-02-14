package com.iut.ecommerce.ecommerce.listener;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.iut.ecommerce.ecommerce.adaptateur.CategorieAdaptateur;
import com.iut.ecommerce.ecommerce.dao.CategorieDao;
import com.iut.ecommerce.ecommerce.fragment.CategorieView;
import com.iut.ecommerce.ecommerce.modele.Categorie;

import static com.iut.ecommerce.ecommerce.Message.SupprimerAlertDialog;

/**
 * Created by Damien on 04/02/2018.
 */

public class DeleteCategorieListener implements View.OnClickListener{

    private Categorie categorie;
    private Context context;

    public DeleteCategorieListener(Categorie categorie, Context context, CategorieAdaptateur categorieAdaptateur) {
        this.categorie = categorie;
        this.context = context;
    }

    @Override
    public void onClick(View view) {

        // On affiche la noite de dialogue pour la suppression du message
        SupprimerAlertDialog(this.context,"Supprimer", "Voulez-vous réellement supprimer l'élément sélectionné : ", this.categorie);
    }

    public static void choix(int choix, Categorie categorie, Context context) {

        if (choix == 1) {

            // On passe à l'unique instance de CategorieDao l'unique instance de CategorieView
            // Il n'existe pour chaque qu'un objet DAO et View!!!
            CategorieView categorieView = CategorieView.getInstance();
            // On passe en paramètre la catégorie à supprimer pour la categorieView
            // On fait ici une suppression dans l'adpateur;
            CategorieAdaptateur.getInstance(context).remove(categorie);
            // On fait ici un appel à la base de données pour supprimer la catégorie
            CategorieDao.getInstance(categorieView).delete(categorie);
            // On mets à jour l'adapteur contenant la liste
            CategorieView.getInstance().liste.remove(categorie);
            // On renvoie l'information que la suppression est terminée
            CategorieView.getInstance().notifyRetourRequete("Supprimer");

        }
    }
}
