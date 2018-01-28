package com.iut.ecommerce.ecommerce.dao;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Miljold on 28/01/2018.
 */

public class AlertSuppression extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        Log.i("dialog","affiche avant");
        Activity activite = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activite);

        builder.setMessage("Etes vous sure de vouloir supprimer?").setTitle("Suppression");
        DialogInterface.OnClickListener ecouteur = (DialogInterface.OnClickListener) activite;
        builder.setPositiveButton("oui", ecouteur);
        builder.setNegativeButton("Non", ecouteur);
        return builder.show();
    }
//    @Override
//    public void onClick(DialogInterface dialog, int which) {
//        if (which == Dialog.BUTTON_POSITIVE) {
//            Log.i("confirmation","valider");
//        }
//    }
}
