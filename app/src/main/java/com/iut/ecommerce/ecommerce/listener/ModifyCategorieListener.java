package com.iut.ecommerce.ecommerce.listener;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.iut.ecommerce.ecommerce.MainActivity;
import com.iut.ecommerce.ecommerce.activity.ModifierCategorieActivity;
import com.iut.ecommerce.ecommerce.dao.CategorieDao;
import com.iut.ecommerce.ecommerce.fragment.CategorieView;
import com.iut.ecommerce.ecommerce.modele.Categorie;

import static android.support.v4.app.ActivityCompat.startActivityForResult;
import static com.iut.ecommerce.ecommerce.Message.SupprimerAlertDialog;

/**
 * Created by Damien on 04/02/2018.
 */

// Pour lancer une autre activité, on doit hériter de AppCompatActivity ou Activity
public class ModifyCategorieListener extends AppCompatActivity implements View.OnClickListener{

    private Categorie categorie;
    private Context context;

    private static final int MODIFICATION_ARTICLE=1;

    public ModifyCategorieListener(Categorie categorie, Context context) {
        this.categorie = categorie;
        this.context = CategorieView.getInstance().getContext();
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this, ModifierCategorieActivity.class);
        intent.putExtra("nom", this.categorie.getNomCateg());
        intent.putExtra("visuel", this.categorie.getVisuelCateg());
        startActivityForResult(intent, MODIFICATION_ARTICLE);

    }
}
