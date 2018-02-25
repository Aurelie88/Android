package com.iut.ecommerce.ecommerce.dao;

import android.util.Log;
import com.iut.ecommerce.ecommerce.modele.Categorie;
import com.iut.ecommerce.ecommerce.utils.ActiviteEnAttenteAvecResultat;
import com.iut.ecommerce.ecommerce.utils.RequeteSQLCategorie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Miljold on 09/01/2018.
 */

public class CategorieDao implements Dao<Categorie> {
    private static final String URL =
            "https://infodb.iutmetz.univ-lorraine.fr/~gaiga4u/ecommerce/categorie/";

    private ActiviteEnAttenteAvecResultat activite;

    // Singleton instance
    private static CategorieDao mInstance = null;

    public static CategorieDao getInstance(ActiviteEnAttenteAvecResultat activite) {
        if(mInstance == null)
        {
            mInstance = new CategorieDao(activite);
        } else {
            if(mInstance.activite != activite){
                mInstance.activite=activite;
            }
        }
        return mInstance;
    }


    private CategorieDao(ActiviteEnAttenteAvecResultat activite) {
        this.activite = activite;
    }

    @Override
    public void findAll() {
        RequeteSQLCategorie req = new RequeteSQLCategorie(activite, this);
        Log.i("_cd", URL+"find.php");
        req.execute(URL + "find.php");
    }


    @Override
    public void create(Categorie categorie) {
        RequeteSQLCategorie req = new RequeteSQLCategorie(activite, this);
        Log.i("_create", "Création d'une nouvelle entrée en base");
        String url = URL + "create.php";
        String params = "?nom="+categorie.getNomCateg()+"&visuel="+categorie.getVisuelCateg();
        Log.i("_cd", url+params);
        req.execute(url+params);
    }

    @Override
    public void update(Categorie categorie) {
        RequeteSQLCategorie req = new RequeteSQLCategorie(activite, this);
        Log.i("_update", "Modification d'une entrée en base");
        String url = URL + "update.php";
        String params = "?id_categorie="+categorie.getIdCateg()+"&nom="+categorie.getNomCateg()+"&visuel="+categorie.getVisuelCateg();
        Log.i("_cd", url+params);
        req.execute(url+params);
    }

    @Override
    public void delete(Categorie categorie) {
        RequeteSQLCategorie req = new RequeteSQLCategorie(activite, this);
        Log.i("_delete", "Suppression d'une entrée en base");
        String url = URL + "delete.php";
        Log.i("iD", String.valueOf(categorie.getIdCateg()));
        String params = "?id_categorie="+categorie.getIdCateg();
        Log.i("_cd", url+params);
        req.execute(url+params);
    }

    @Override
    public void traiteFindAll(String result){
        ArrayList<Categorie> liste = new ArrayList<Categorie>();

        try{
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject row = array.getJSONObject(i);
                Categorie c = new Categorie(row.getInt("id_categorie"),
                        row.getString("nom"),
                        row.getString("visuel"));
                liste.add(c);

            }
            this.activite.notifyRetourRequeteFindAll(liste);

        } catch (JSONException je) {
            System.out.println("Pb json : " + je);
        }


    }
}

