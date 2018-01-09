package com.iut.ecommerce.ecommerce.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.iut.ecommerce.ecommerce.modele.Commande;


/**
 * Created by Miljold on 09/01/2018.
 */

public class CommandeDao {

    private static final String TABLE_COMMANDE = "table_commande";
    private static final String COL_ID_COMMANDE = "id_commande";
    private static final String COL_DATE = "date";
    private static final String COL_ID_CLIENT= "id_client";

    private BaseDeDonnees maBaseDeDonnees;

    private SQLiteDatabase bdd;

    public CommandeDao(Context context){
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

    public long insertCommande(Commande commande){
        //Insertion Commande
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)

        values.put(COL_DATE, String.valueOf(commande.getDate()));
        values.put(COL_ID_CLIENT, commande.getIdClient());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_COMMANDE, null, values);
    }

    public int updateCommande(int id, Commande commande){
        //La mise à jour d'une Commande dans la BDD
        //il faut simplement préciser quel article on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_DATE, String.valueOf(commande.getDate()));
        values.put(COL_ID_CLIENT, commande.getIdClient());
        return bdd.update(TABLE_COMMANDE, values, COL_ID_COMMANDE + " = " +id, null);
    }

    public int removeCommandeWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_COMMANDE, COL_ID_COMMANDE + " = " +id, null);
    }
}
