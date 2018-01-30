package com.iut.ecommerce.ecommerce.dao;

import android.util.Log;
import android.view.View;

/**
 * Created by Miljold on 28/01/2018.
 */

public class imageModifierClickListener implements View.OnClickListener {
    int position;
    public imageModifierClickListener(int pos) {
        this.position=pos;
    }
    public void onClick(View v){
        Log.i("modifier", String.valueOf(this.position));
    }
}
