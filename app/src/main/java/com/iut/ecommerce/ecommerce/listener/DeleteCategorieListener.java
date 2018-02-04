package com.iut.ecommerce.ecommerce.listener;

import android.content.Context;
import android.view.View;

import com.iut.ecommerce.ecommerce.dao.CategorieDao;
import com.iut.ecommerce.ecommerce.fragment.CategorieView;
import com.iut.ecommerce.ecommerce.modele.Categorie;
import com.iut.ecommerce.ecommerce.modele.Generique;

import static com.iut.ecommerce.ecommerce.Message.SupprimerAlertDialog;

/**
 * Created by Damien on 04/02/2018.
 */

public class DeleteCategorieListener implements View.OnClickListener{

    private Categorie categorie;
    private Context context;
    public static boolean delete = false;

    public DeleteCategorieListener(Categorie categorie, Context context) {
        this.categorie = categorie;
        this.context = context;
    }

    @Override
    public void onClick(View view) {

        // On affiche la noite de dialogue pour la suppression du message
        SupprimerAlertDialog(this.context,"Supprimer", "Voulez-vous réellement supprimer l'élément sélectionné : ", this.categorie);

        // Si l'utilisateur valide la suppression, on efface dans la base de données
        if (delete){
            // On passe à l'unique instance de CategorieDao l'unique instance de CategorieView
            // Il n'existe pour chaque qu'un objet DAO et View!!!
            CategorieView categorieView = CategorieView.getInstance();
            // On passe en paramètre la catégorie à supprimer pour la categorieView
            CategorieDao.getInstance(categorieView).delete(this.categorie);
            // On mets à jour la liste
            CategorieView.getInstance().notifyRetourRequete(this.categorie.getNomCateg()+" a été effacé");
        }

        // Pour être sur de ne pas effacer des éléments de manière répétitive, on remet
        // le flag delete à false
        delete = false;
    }
}
