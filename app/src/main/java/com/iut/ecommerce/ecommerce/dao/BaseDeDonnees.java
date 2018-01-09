package com.iut.ecommerce.ecommerce.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Miljold on 09/01/2018.
 */

public class BaseDeDonnees extends SQLiteOpenHelper {

    //TABLE ARTICLE
    private static final String TABLE_ARTICLE = "table_article";
    private static final String COL_ID_ARTICLE = "id_article";
    private static final String COL_REFERENCE = "reference";
    private static final String COL_TARIF = "tarif";
    private static final String COL_VISUEL_ARTICLE = "visuel_article";

    //TABLE CATEGORIE
    private static final String TABLE_CATEGORIE = "table_categorie";
    private static final String COL_ID_CATEGORIE = "id_categorie";
    private static final String COL_NOM_CATEGORIE ="nom_categorie";
    private static final String COL_VISUEL_CATEGORIE = "visuel_categorie";

    //TABLE CLIENT
    private static final String TABLE_CLIENT = "table_client";
    private static final String COL_ID_CLIENT = "id_client";
    private static final String COL_NOM_CLIENT = "nom_client";
    private static final String COL_PRENOM_CLIENT = "prenom_client";
    private static final String COL_VILLE = "ville";

    //TABLE COMMANDE
    private static final String TABLE_COMMANDE = "table_commande";
    private static final String COL_ID_COMMANDE = "id_commande";
    private static final String COL_DATE = "date";
    //private static final String COL_ID_CLIENT_E = "id_client";

    //TABLE LIGNE_COMMANDE
    private static final String TABLE_LIGNE_COMMANDE = "table_ligne_commande";
    //private static final String COL_ID_COMMANDE_E = "id_commande";
    private static final String COL_ID_LIGNE = "ligne";
    //private static final String COL_ID_ARTICLE_E = "id_article";
    private static final String COL_QUANTITE="quantite";

    //TABLE PROMO
    private  static final String TABLE_PROMOTION = "table_promotion";
    private static final String COL_DATE_DEBUT = "date_debut";
    private static final String COL_DATE_FIN = "date_fin";
    private static final String COL_POURCENTAGE= "pourcent";

    //REQUETE CREATION TABLE
    private static final String CREATE_TABLE_ARTICLE=String.format("CREATE TABLE %s " +
                    "(%s INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "%s INTEGER NOT NULL, " +
                    "%s FLOAT NOT NULL, " +
                    "%s VARCHAR(255)NOT NULL)",
            TABLE_ARTICLE,COL_ID_ARTICLE, COL_REFERENCE, COL_TARIF, COL_VISUEL_ARTICLE);

    private static final String CREATE_TABLE_CATEGORIE=String.format("CREATE TABLE %s " +
                    "(%s INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "%s VARCHAR(255)NOT NULL,"+
                    "%s VARCHAR(255)NOT NULL",
            TABLE_CATEGORIE,COL_ID_CATEGORIE, COL_NOM_CATEGORIE, COL_VISUEL_CATEGORIE );

    private static final String CREATE_TABLE_CLIENT=String.format("CREATE TABLE %s " +
                    "(%s INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "%s VARCHAR(255)NOT NULL,"+
                    "%s VARCHAR(255)NOT NULL,"+
                    "%s VARCHAR(255)NOT NULL)",
            TABLE_CLIENT,COL_ID_CLIENT, COL_NOM_CLIENT, COL_PRENOM_CLIENT, COL_VILLE );

    private static final String CREATE_TABLE_COMMANDE=String.format("CREATE TABLE %s " +
                    "(%s INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "%s DATE NOT NULL,"+
                    "%s INTEGER NOT NULL)"+
            TABLE_COMMANDE,COL_ID_COMMANDE, COL_DATE, COL_ID_CLIENT);

    private static final String CREATE_TABLE__LIGNE_COMMANDE=String.format("CREATE TABLE %s " +
            "(%s INTEGER NOT NULL " +
            "%s INTEGER NOT NULL,"+
            "%s INTEGER NOT NULL,"+
            "%s INTEGER NOT NULL,PRIMARY KEY(%s,%s))"+
            TABLE_LIGNE_COMMANDE,COL_ID_COMMANDE, COL_ID_LIGNE, COL_ID_ARTICLE, COL_QUANTITE, COL_ID_LIGNE, COL_ID_COMMANDE);

    private static final String CREATE_TABLE_PROMOTION=String.format("CREATE TABLE %s " +
            "(%s INTEGER NOT NULL, " +
            "%s DATE NOT NULL,"+
            "%s DATE NOT NULL,"+
            "%s FLOAT NOT NULL, PRIMARY KEY(%s,%s))"+
            TABLE_PROMOTION, COL_ID_ARTICLE, COL_DATE_DEBUT, COL_DATE_FIN, COL_POURCENTAGE, COL_ID_ARTICLE, COL_DATE_DEBUT);

    public BaseDeDonnees(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public BaseDeDonnees(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ARTICLE);
        db.execSQL(CREATE_TABLE_CATEGORIE);
        db.execSQL(CREATE_TABLE_CLIENT);
        db.execSQL(CREATE_TABLE_COMMANDE);
        db.execSQL(CREATE_TABLE__LIGNE_COMMANDE);
        db.execSQL(CREATE_TABLE_PROMOTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
