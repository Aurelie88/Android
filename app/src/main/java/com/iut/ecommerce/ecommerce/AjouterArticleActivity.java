package com.iut.ecommerce.ecommerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.iut.ecommerce.ecommerce.dao.CategorieDao;
import com.iut.ecommerce.ecommerce.fragment.CategorieView;
import com.iut.ecommerce.ecommerce.modele.Categorie;
import com.iut.ecommerce.ecommerce.utils.ActiviteEnAttente;
import com.iut.ecommerce.ecommerce.utils.ActiviteEnAttenteAvecResultat;

public class AjouterArticleActivity extends AppCompatActivity implements ActiviteEnAttente{

    private TextView designation;
    private TextView reference;
    private TextView tarif;
    private EditText editDesignation;
    private EditText editReference;
    private EditText editTarif;

    private ActiviteEnAttente activite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_categorie);

        EditText editDesignation = findViewById(R.id.et_nomCategorie);
        EditText editTarif = findViewById(R.id.et_visuelCategorie);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id==android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void action(View view) {

        Log.i("Valider", "Appui sur le bouton de validation");

        Categorie categorie = new Categorie(editDesignation.getText().toString(), editTarif.getText().toString());

        CategorieDao.getInstance((ActiviteEnAttenteAvecResultat) activite).create(categorie);


    }

    @Override
    public void notifyRetourRequete(String resultat) {

    }
}
