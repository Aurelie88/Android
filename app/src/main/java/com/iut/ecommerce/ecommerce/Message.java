package com.iut.ecommerce.ecommerce;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.iut.ecommerce.ecommerce.listener.DeleteCategorieListener;
import com.iut.ecommerce.ecommerce.modele.Article;
import com.iut.ecommerce.ecommerce.modele.Categorie;
import com.iut.ecommerce.ecommerce.modele.Promotion;

/**
 * Created by Damien on 04/02/2018.
 */

public class Message {

    static int NO = 0;
    static int YES = 1;

    // Boîte de dialogue d'alerte pour la suppression
    // Si implémentation dans une autre classe, penser à ajouter le context en paramètre
    public static void supprimerAlertDialog(final Context context, String title, String message, final Object object, DialogInterface.OnClickListener ecouteur) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);

        if (object instanceof Categorie){
            Categorie o = (Categorie) object;
            builder.setMessage(message + o.getNomCateg());
        } else if (object instanceof Article) {
            Article o = (Article) object;
            builder.setMessage(message + o.getNomArticle());
        } else {
            Promotion o = (Promotion) object;
            builder.setMessage(message + o.getPourcentage());
        }

        builder.setPositiveButton(android.R.string.yes, ecouteur);
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // On ne fait rien
                dialog.dismiss();
            }
        });
        // On crée et on affiche la boîte de dialogue
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void afficheMessageSnack(View view, String text, int duration){
        Snackbar.make(view, text, duration)
                .setAction("Action", null).show();
    }

}
