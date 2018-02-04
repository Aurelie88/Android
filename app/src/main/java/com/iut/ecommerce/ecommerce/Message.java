package com.iut.ecommerce.ecommerce;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.iut.ecommerce.ecommerce.listener.DeleteCategorieListener;
import com.iut.ecommerce.ecommerce.modele.Categorie;

/**
 * Created by Damien on 04/02/2018.
 */

public class Message {

    // Boîte de dialogue d'alerte pour la suppression
    // Si implémentation dans une autre classe, penser à ajouter le context en paramètre
    public static void SupprimerAlertDialog(Context context, String title, String message, Categorie currentCategorie) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message + currentCategorie.getNomCateg());
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                DeleteCategorieListener.delete = true;
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // On ne fait rien
                DeleteCategorieListener.delete = false;
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void afficheMessageSnack(View view, String text, int duration){
        Snackbar.make(view, text, duration)
                .setAction("Action", null).show();
    }

}
