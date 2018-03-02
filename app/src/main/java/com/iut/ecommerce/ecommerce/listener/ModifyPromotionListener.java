package com.iut.ecommerce.ecommerce.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.iut.ecommerce.ecommerce.activity.AjouterPromotionActivity;
import com.iut.ecommerce.ecommerce.modele.Promotion;

/**
 * Created by Damien on 23/02/2018.
 */

public class ModifyPromotionListener implements View.OnClickListener {
    private Promotion promotion;
    private Context context;

    private static final int MODIFICATION_PROMOTION=6;

    public ModifyPromotionListener(Promotion promotion, Context context) {
        // NOTA : Le context est celui de la vue (widget)! D'où le view.getContext() de
        // la classe appelante.
        this.promotion = promotion;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this.context, AjouterPromotionActivity.class);
        intent.putExtra("nom", this.promotion);
        // Le cast permet d'accéder à la méthode startActivityForResult
        ((Activity) this.context).startActivityForResult(intent, MODIFICATION_PROMOTION);
    }
}
