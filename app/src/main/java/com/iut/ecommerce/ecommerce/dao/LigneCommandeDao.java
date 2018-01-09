package com.iut.ecommerce.ecommerce.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.iut.ecommerce.ecommerce.modele.LigneCommande;

/**
 * Created by Miljold on 09/01/2018.
 */

public class LigneCommandeDao {
    private static final String TABLE_LIGNE_COMMANDE = "table_ligne_commande";
    private static final String COL_ID_COMMANDE = "id_commande";
    private static final String COL_ID_LIGNE = "ligne";
    private static final String COL_ID_ARTICLE = "id_article";
    private static final String COL_QUANTITE="quantite";



    private BaseDeDonnees maBaseDeDonnees;

    private SQLiteDatabase bdd;

    public LigneCommandeDao(Context context){
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

    public long insertLigneCommande(LigneCommande ligneCommande){
        //Insertion Article
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_ID_COMMANDE, ligneCommande.getIdCommande());
        values.put(COL_ID_LIGNE, ligneCommande.getIdLigne());
        values.put(COL_ID_ARTICLE, ligneCommande.getIdArticle());
        values.put(COL_QUANTITE, ligneCommande.getQuantite());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_LIGNE_COMMANDE, null, values);
    }
    //COL_ID_COMMANDE, COL_ID_LIGNE, COL_ID_ARTICLE, COL_QUANTITE, COL_ID_LIGNE, COL_ID_COMMANDE
    public int updateLigneCommande(int idLigne, int idComm, LigneCommande ligneCommande){
        //La mise à jour d'un article dans la BDD
        //il faut simplement préciser quel article on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_ID_COMMANDE, ligneCommande.getIdCommande());
        values.put(COL_ID_LIGNE, ligneCommande.getIdLigne());
        values.put(COL_ID_ARTICLE, ligneCommande.getIdArticle());
        values.put(COL_QUANTITE, ligneCommande.getQuantite());
        return bdd.update(TABLE_LIGNE_COMMANDE, values, COL_ID_COMMANDE + " = " +idComm + "AND " + COL_ID_LIGNE + "=" +idLigne, null);
    }

    public int removeLigneCommandeWithID(int idComm, int idLigne){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_LIGNE_COMMANDE, COL_ID_COMMANDE + " = " +idComm + "AND " + COL_ID_LIGNE + "=" +idLigne, null);
    }
}
