package com.iut.ecommerce.ecommerce.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.iut.ecommerce.ecommerce.dao.CategorieDao;
import com.iut.ecommerce.ecommerce.dao.Dao;
import com.iut.ecommerce.ecommerce.modele.Categorie;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Damien on 25/01/2018.
 */

public class RequeteSQLCategorie extends AsyncTask<String, Void, String>{

    private ActiviteEnAttenteAvecResultat activite;
    private CategorieDao dao;

    public RequeteSQLCategorie(ActiviteEnAttenteAvecResultat activite, CategorieDao dao){
        this.activite = activite;
        this.dao = dao;
    }

    @Override
    protected String doInBackground(String... urls) {

        Log.i("dib", urls[0]);
        String urlRequete = urls[0];
        StringBuffer resultat=new StringBuffer(1024);
        try{
            final HttpsURLConnection connection = (HttpsURLConnection) new URL(urlRequete).openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            connection.connect();
            InputStream input = connection.getInputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            String line = "";
            while ((line=in.readLine()) != null){
                resultat.append(line);
            }
            in.close();

        } catch (Exception e){
            return "Erreur : "+e.getMessage();
        }
        return resultat.toString();
    }

    @Override
    protected void onPostExecute(String result) {

        Log.i("_R", result);

        if (result.startsWith("[")) {
            // Si on vient de faire une requete retournant un résultat au format JSON
            this.dao.traiteFindAll(result);
        } else {
            // Si on vient de faire une Create/Update/Remove
            this.activite.notifyRetourRequete(result);
        }

    }
}
