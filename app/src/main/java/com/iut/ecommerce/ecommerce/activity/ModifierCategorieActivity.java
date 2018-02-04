package com.iut.ecommerce.ecommerce.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.iut.ecommerce.ecommerce.R;
import com.iut.ecommerce.ecommerce.utils.ActiviteEnAttente;

public class ModifierCategorieActivity extends AppCompatActivity {
    private TextView tv_nomCategorie;
    private EditText et_nomCategorie;
    private TextView tv_visuelCategorie;
    private EditText et_visuelCategorie;
    private ActiviteEnAttente activite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_categorie);
    }


    @Override
    protected void onStart() {
        super.onStart();

        // NOTA : Le cast n'est plus nécessaire
        this.tv_nomCategorie=(TextView) this.findViewById(R.id.tv_nomCategorie);
        this.et_nomCategorie=(EditText) this.findViewById(R.id.et_nomCategorie);
        this.tv_visuelCategorie=(TextView) this.findViewById(R.id.tv_visuelCategorie);
        this.et_visuelCategorie = (EditText) this.findViewById(R.id.et_visuelCategorie);

        // On configure les textView pour afficher les bons messages
        this.et_nomCategorie.setText(this.getIntent().getStringExtra("nom"));
        this.et_visuelCategorie.setText(this.getIntent().getStringExtra("visuel"));


        // A UTILISER POUR FUSIONNER LES ACTIVITES SIMILAIRES
        /*
        // On teste si extra est non null
        if (this.getIntent().getExtras() == null){
            // Si null, c'est que l'on veut créer une devise
            // On configure la textView pour afficher le bon message
            tvAffichage.setText(R.string.addDevise);
        } else {
            // Sinon, c'est une modification
            // On configure la textView pour afficher le bon message
            tvAffichage.setText(R.string.majDevise);
            etNom.setText(this.getIntent().getStringExtra("nom"));
        }
        */
    }

}
