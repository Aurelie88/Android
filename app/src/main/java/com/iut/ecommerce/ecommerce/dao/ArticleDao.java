package com.iut.ecommerce.ecommerce.dao;


import android.util.Log;

import com.iut.ecommerce.ecommerce.fragment.ArticleView;
import com.iut.ecommerce.ecommerce.modele.Article;
import com.iut.ecommerce.ecommerce.utils.ActiviteEnAttenteAvecResultat;
import com.iut.ecommerce.ecommerce.utils.RequeteSQLArticle;
import com.iut.ecommerce.ecommerce.utils.RequeteSQLCategorie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Miljold on 09/01/2018.
 */

public class ArticleDao implements Dao<Article> {
    private static final String URL =
            "https://infodb.iutmetz.univ-lorraine.fr/~gaiga4u/ecommerce/article/";

    private ActiviteEnAttenteAvecResultat activite;

    // Singleton instance
    private static ArticleDao mInstance = null;

    public static ArticleDao getInstance(ArticleView activite) {
        if(mInstance == null)
        {
            mInstance = new ArticleDao(activite);
        } else {
            if(mInstance.activite != activite){
                mInstance.activite=activite;
            }
        }
        return mInstance;
    }


    private ArticleDao(ArticleView activite) {
        this.activite = activite;
    }

    @Override
    public void findAll() {
        RequeteSQLArticle req = new RequeteSQLArticle(activite, this);
        req.execute(URL + "find.php");
    }


    @Override
    public void create(Article article) {
        RequeteSQLArticle req = new RequeteSQLArticle(activite, this);
        Log.i("create", "Création d'une nouvelle entrée en base");
        String url = URL + "create.php";
        String params = "?nom="+article.getNomArticle()+"&visuel="+article.getVisuelArticle();
        req.execute(url+params);
    }

    @Override
    public void update(Article article) {
        RequeteSQLArticle req = new RequeteSQLArticle(activite, this);
        Log.i("update", "Modification d'une entrée en base");
        String url = URL + "update.php";
        String params = "?id_categorie="+article.getNomArticle()+"?nom="+article.getNomArticle()+"&visuel="+article.getVisuelArticle();
        req.execute(url+params);
    }

    @Override
    public void delete(Article article) {
        RequeteSQLArticle req = new RequeteSQLArticle(activite, this);
        Log.i("delete", "Suppression d'une entrée en base");
        String url = URL + "delete.php";
        Log.i("iD", String.valueOf(article.getIdArticle()));
        String params = "?id_categorie="+article.getIdArticle();
        req.execute(url+params);
    }


    @Override
    public void traiteFindAll(String result){
        ArrayList<Article> liste = new ArrayList<Article>();

        try{

            Log.i("_ta", "Traitement du JSON article");

            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject row = array.getJSONObject(i);
                Article c = new Article(row.getInt("id_article"),
                        row.getString("reference"),
                        row.getString("nom"),
                        row.getLong("tarif"),
                        row.getString("visuel"),
                        row.getInt("id_categorie_fk"));
                liste.add(c);

            }
            this.activite.notifyRetourRequeteFindAll(liste);

        } catch (JSONException je) {
            Log.i("_ta", "ERREUR - Traitement du JSON article");
            System.out.println("Pb json : " + je);
        }


    }
}
