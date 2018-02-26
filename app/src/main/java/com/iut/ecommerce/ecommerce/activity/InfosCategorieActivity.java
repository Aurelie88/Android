package com.iut.ecommerce.ecommerce.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.iut.ecommerce.ecommerce.R;
import com.iut.ecommerce.ecommerce.modele.Categorie;
import com.squareup.picasso.Picasso;

public class InfosCategorieActivity extends AppCompatActivity {

    private Categorie categorie = null;
    private Button retour;
    private TextView nomCategorie;
    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos_categorie);


        this.retour = (Button) findViewById(R.id.retour);
        this.nomCategorie = (TextView) findViewById(R.id.nomCategorie);
        this.imageview = (ImageView) findViewById(R.id.imageview);
    }


    @Override
    protected void onStart() {
        super.onStart();

        categorie = (Categorie) this.getIntent().getSerializableExtra("categorie");

        nomCategorie.setText(categorie.getNomCateg());
        // On set l'image
        Picasso
                .with(this)
                .load("https://infodb.iutmetz.univ-lorraine.fr/~gaiga4u/ecommerce/" + categorie.getVisuelCateg())
                .placeholder(R.drawable.ic_close) // can also be a drawable
                .error(R.drawable.ic_close) // will be displayed if the image cannot be loaded
                .into(this.imageview);


        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
