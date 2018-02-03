package com.iut.ecommerce.ecommerce.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.iut.ecommerce.ecommerce.R;
import com.iut.ecommerce.ecommerce.dao.CategorieDao;
import com.iut.ecommerce.ecommerce.modele.Categorie;
import com.iut.ecommerce.ecommerce.utils.ActiviteEnAttente;
import com.iut.ecommerce.ecommerce.utils.ActiviteEnAttenteAvecResultat;

public class ModifierCategorieActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_nomCategorie;
    private EditText et_nomCategorie;
    private TextView tv_visuelCategorie;
    private EditText et_visuelCategorie;
    private ActiviteEnAttente activite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_categorie);
        this.tv_nomCategorie=(TextView) this.findViewById(R.id.tv_nomCategorie);
        this.et_nomCategorie=(EditText) this.findViewById(R.id.et_nomCategorie);
        this.tv_visuelCategorie=(TextView) this.findViewById(R.id.tv_visuelCategorie);
        this.et_visuelCategorie = (EditText) this.findViewById(R.id.et_visuelCategorie);
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

        Categorie categorie = new Categorie(et_nomCategorie.getText().toString(), et_visuelCategorie.getText().toString());
        CategorieDao.getInstance((ActiviteEnAttenteAvecResultat) activite).create(categorie);
    }

    @Override
    public void onClick(View view) {

    }
}
