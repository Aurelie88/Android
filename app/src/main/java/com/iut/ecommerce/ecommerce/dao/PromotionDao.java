package com.iut.ecommerce.ecommerce.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.iut.ecommerce.ecommerce.modele.Promotion;

import java.sql.Date;


/**
 * Created by Miljold on 09/01/2018.
 */

public class PromotionDao {
    private  static final String TABLE_PROMOTION = "table_promotion";
    private static final String COL_DATE_DEBUT = "date_debut";
    private static final String COL_DATE_FIN = "date_fin";
    private static final String COL_POURCENTAGE= "pourcent";
    private static final String COL_ID_ARTICLE = "id_article";

    private BaseDeDonnees maBaseDeDonnees;

    private SQLiteDatabase bdd;

    public PromotionDao(Context context){
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

    public long insertArticle(Promotion promotion){
        //Insertion Article
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_ID_ARTICLE, promotion.getIdArticle());
        values.put(COL_DATE_DEBUT, String.valueOf(promotion.getDateDebut()));
        values.put(COL_DATE_FIN, String.valueOf(promotion.getDateFin()));
        values.put(COL_POURCENTAGE, promotion.getPourcentage());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_PROMOTION, null, values);
    }
//COL_ID_ARTICLE, COL_DATE_DEBUT, COL_DATE_FIN, COL_POURCENTAGE, COL_ID_ARTICLE, COL_DATE_DEBUT);
    public int updateArticle(int idArticle, Date dateDebut, Promotion promotion){
        //La mise à jour d'un article dans la BDD
        //il faut simplement préciser quel article on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_ID_ARTICLE, promotion.getIdArticle());
        values.put(COL_DATE_DEBUT, String.valueOf(promotion.getDateDebut()));
        values.put(COL_DATE_FIN, String.valueOf(promotion.getDateFin()));
        values.put(COL_POURCENTAGE, promotion.getPourcentage());
        return bdd.update(TABLE_PROMOTION, values, COL_ID_ARTICLE + " = " +idArticle+" AND "+ COL_DATE_DEBUT +" = "+dateDebut, null);
    }

    public int removeArticleWithID(int idArticle, Date dateDebut){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_PROMOTION, COL_ID_ARTICLE + " = " +idArticle+" AND "+ COL_DATE_DEBUT +" = "+dateDebut,null);
    }
}
