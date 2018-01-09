package com.iut.ecommerce.ecommerce.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.iut.ecommerce.ecommerce.modele.Client;


/**
 * Created by Miljold on 09/01/2018.
 */

public class ClientDao {

    private static final String TABLE_CLIENT = "table_client";
    private static final String COL_ID_CLIENT = "id_client";
    private static final String COL_NOM_CLIENT = "nom_client";
    private static final String COL_PRENOM_CLIENT = "prenom_client";
    private static final String COL_VILLE = "ville";

    private BaseDeDonnees maBaseDeDonnees;

    private SQLiteDatabase bdd;

    public ClientDao(Context context){
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

    public long insertClient(Client client){
        //Insertion Article
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_NOM_CLIENT, client.getNom());
        values.put(COL_PRENOM_CLIENT, client.getPrenom());
        values.put(COL_VILLE, client.getVille());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_CLIENT, null, values);
    }
//COL_NOM_CLIENT, COL_PRENOM_CLIENT, COL_VILLE
    public int updateClient(int id, Client client){
        //La mise à jour d'un article dans la BDD
        //il faut simplement préciser quel article on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_NOM_CLIENT, client.getNom());
        values.put(COL_PRENOM_CLIENT, client.getPrenom());
        values.put(COL_VILLE, client.getVille());
        return bdd.update(TABLE_CLIENT, values, COL_ID_CLIENT + " = " +id, null);
    }

    public int removeArticleWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_CLIENT, COL_ID_CLIENT + " = " +id, null);
    }
}
