package com.iut.ecommerce.ecommerce.dao;

import android.app.Dialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
/**
 * Created by Miljold on 28/01/2018.
 */

class imageSupprimerClickListener implements View.OnClickListener {
    int position;
    public imageSupprimerClickListener(int pos) {
        this.position=pos;
    }

    public void onClick(View view){
        Log.i("supprimer", String.valueOf(this.position));
        new AlertSuppression();
        //alerte.show();
    }

}
