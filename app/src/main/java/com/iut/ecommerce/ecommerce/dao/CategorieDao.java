package com.iut.ecommerce.ecommerce.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.iut.ecommerce.ecommerce.modele.Categorie;

/**
 * Created by Miljold on 09/01/2018.
 */

public class CategorieDao {
    private static final String TABLE_CATEGORIE = "table_categorie";
    private static final String COL_ID_CATEGORIE = "id_categorie";
    private static final String COL_NOM_CATEGORIE ="nom_categorie";
    private static final String COL_VISUEL_CATEGORIE = "visuel_categorie";

    private BaseDeDonnees maBaseDeDonnees;

    private SQLiteDatabase bdd;

    public CategorieDao(Context context){
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

    public long insertCategorie(Categorie categorie){
        //Insertion Categorie
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)

        values.put(COL_NOM_CATEGORIE, categorie.getNomCateg());
        values.put(COL_VISUEL_CATEGORIE,categorie.getVisuelCateg());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_CATEGORIE, null, values);
    }

    public int updateCategorie(int id, Categorie categorie){
        //La mise à jour d'un article dans la BDD
        //il faut simplement préciser quel article on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_NOM_CATEGORIE, categorie.getNomCateg());
        values.put(COL_VISUEL_CATEGORIE,categorie.getVisuelCateg());
        return bdd.update(TABLE_CATEGORIE, values, COL_ID_CATEGORIE + " = " +id, null);
    }

    public int removeCategorieWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_CATEGORIE, COL_ID_CATEGORIE + " = " +id, null);
    }
}