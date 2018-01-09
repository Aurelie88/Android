package com.iut.ecommerce.ecommerce.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.ContextCompat;

import com.iut.ecommerce.ecommerce.modele.Article;

/**
 * Created by Miljold on 09/01/2018.
 */

public class ArticleDao {
    private static final String TABLE_ARTICLE = "table_article";
    private static final String COL_ID_ARTICLE = "id_article";
    private static final String COL_REFERENCE = "reference";
    private static final String COL_TARIF = "tarif";
    private static final String COL_NOM_ARTICLE="nom_article";
    private static final String COL_VISUEL_ARTICLE = "visuel_article";

    private BaseDeDonnees maBaseDeDonnees;

    private SQLiteDatabase bdd;

    public ArticleDao(Context context){
        //On crée la BDD et sa table
    maBaseDeDonnees = new BaseDeDonnees(context, "eCommerce.db", null, 1);
}

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseDeDonnees.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertArticle(Article article){
        //Insertion Article
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_REFERENCE, article.getReference());
        values.put(COL_NOM_ARTICLE, article.getNomArticle());
        values.put(COL_TARIF, article.getTarif());
        values.put(COL_VISUEL_ARTICLE, article.getVisuelArticle());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_ARTICLE, null, values);
    }

    public int updateArticle(int id, Article article){
        //La mise à jour d'un article dans la BDD
        //il faut simplement préciser quel article on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_REFERENCE, article.getReference());
        values.put(COL_NOM_ARTICLE, article.getNomArticle());
        values.put(COL_TARIF, article.getTarif());
        values.put(COL_VISUEL_ARTICLE, article.getVisuelArticle());
        return bdd.update(TABLE_ARTICLE, values, COL_ID_ARTICLE + " = " +id, null);
    }

    public int removeArticleWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_ARTICLE, COL_ID_ARTICLE + " = " +id, null);
    }
}
