package com.iut.ecommerce.ecommerce.dao;

import android.util.Log;

import com.iut.ecommerce.ecommerce.modele.Categorie;
import com.iut.ecommerce.ecommerce.utils.ActiviteEnAttenteAvecResultat;
import com.iut.ecommerce.ecommerce.utils.RequeteSQL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Miljold on 09/01/2018.
 */

public class CategorieDao implements Dao<Categorie> {
    private static final String URL =
            "https://infodb.iutmetz.univ-lorraine.fr/~gaiga4u/ecommerce/";

    private ActiviteEnAttenteAvecResultat activite;

    // Singleton instance
    private static CategorieDao mInstance = null;

    public static CategorieDao getInstance(ActiviteEnAttenteAvecResultat activite) {
        if(mInstance == null)
        {
            mInstance = new CategorieDao(activite);
        }
        return mInstance;
    }


    private CategorieDao(ActiviteEnAttenteAvecResultat activite) {
        this.activite = activite;
    }

    @Override
    public void findAll() {
        RequeteSQL req = new RequeteSQL(activite, this);
        req.execute(URL + "getCategorie.php");
    }


    @Override
    public void create(Categorie categorie) {

        RequeteSQL req = new RequeteSQL(activite, this);
        Log.i("create", "Création d'une nouvelle entrée en base");
        String url = URL + "updateCategorie.php";
        String params = "?nom="+categorie.getNomCateg()+"&visuel="+categorie.getVisuelCateg();
        req.execute(url+params);


    }

    @Override
    public void update(Categorie categorie) {


    }

    @Override
    public void delete(Categorie categorie) {

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
