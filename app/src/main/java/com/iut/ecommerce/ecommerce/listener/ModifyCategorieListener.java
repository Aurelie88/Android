package com.iut.ecommerce.ecommerce.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.iut.ecommerce.ecommerce.activity.ModifierCategorieActivity;
import com.iut.ecommerce.ecommerce.fragment.CategorieView;
import com.iut.ecommerce.ecommerce.modele.Categorie;

/**
 * Created by Damien on 04/02/2018.
 */

// Pour lancer une autre activité, on doit hériter de AppCompatActivity ou Activity
public class ModifyCategorieListener extends AppCompatActivity implements View.OnClickListener{

    private Categorie categorie;
    private Context context;

    private static final int MODIFICATION_ARTICLE=1;

    public ModifyCategorieListener(Categorie categorie, Context context) {
        // NOTA : Le context est celui de la vue (widget)! D'où le view.getContext() de
        // la classe appelante.
        this.categorie = categorie;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this.context, ModifierCategorieActivity.class);
        intent.putExtra("nom", this.categorie.getNomCateg());
        intent.putExtra("visuel", this.categorie.getVisuelCateg());
        // Le cast permet d'accéder à la méthode startActivityForResult
        ((Activity) this.context).startActivityForResult(intent, MODIFICATION_ARTICLE);
    }
}
