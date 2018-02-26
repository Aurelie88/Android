package com.iut.ecommerce.ecommerce.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.iut.ecommerce.ecommerce.R;
import com.iut.ecommerce.ecommerce.modele.Article;
import com.iut.ecommerce.ecommerce.modele.Categorie;
import com.squareup.picasso.Picasso;

public class InfosArticleActivity extends AppCompatActivity {

    private Article article = null;
    private Button retour;
    private TextView nomCategorie;
    private ImageView imageview;
    private TextView reference;
    private TextView prix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos_article);

        this.retour = (Button) findViewById(R.id.retour);
        this.nomCategorie = (TextView) findViewById(R.id.nomCategorie);
        this.reference = (TextView) findViewById(R.id.reference);
        this.prix = (TextView) findViewById(R.id.prixArticle);
        this.nomCategorie = (TextView) findViewById(R.id.nomCategorie);
        this.imageview = (ImageView) findViewById(R.id.imageview);
    }


    @Override
    protected void onStart() {
        super.onStart();

        article = (Article) this.getIntent().getSerializableExtra("article");
        reference.setText(article.getReference());
        prix.setText(String.valueOf(article.getTarif()));
        //nomCategorie.setText(article.getIdCategorie());
        // On set l'image
        Picasso
                .with(this)
                .load("https://infodb.iutmetz.univ-lorraine.fr/~gaiga4u/ecommerce/" + article.getVisuelArticle())
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
