package com.iut.ecommerce.ecommerce.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.iut.ecommerce.ecommerce.R;
import com.iut.ecommerce.ecommerce.fragment.CategorieView;
import com.iut.ecommerce.ecommerce.modele.Article;
import com.iut.ecommerce.ecommerce.modele.Categorie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class InfosArticleActivity extends AppCompatActivity {

    private Article article = null;
    private Button retour;
    private TextView nomCategorie;
    private ImageView imageview;
    private TextView reference;
    private TextView prix;
    private TextView nomArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infos_article);

        this.retour = (Button) findViewById(R.id.retour);
        this.nomArticle = (TextView) findViewById(R.id.nomArticle);
        this.reference = (TextView) findViewById(R.id.reference);
        this.prix = (TextView) findViewById(R.id.prixArticle);
        this.nomCategorie = (TextView) findViewById(R.id.list_Categorie);
        this.imageview = (ImageView) findViewById(R.id.imageview);
    }

    @Override
    protected void onStart() {
        super.onStart();

        article = (Article) this.getIntent().getSerializableExtra("article");
        nomArticle.setText(article.getNomArticle());
        reference.setText(article.getReference());
        prix.setText(String.valueOf(article.getTarif())+"€");

        // Pour retrouver la catégorie parente, on récupère la liste des catégories
        ArrayList<Categorie> temp = CategorieView.getInstance().liste;
        for (Categorie i: temp){
            if(i.getIdCateg()==article.getIdCategorie()){
                nomCategorie.setText(String.valueOf(i.getNomCateg()));
            }
        }
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
