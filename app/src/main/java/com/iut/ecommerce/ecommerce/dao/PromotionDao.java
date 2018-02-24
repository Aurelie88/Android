package com.iut.ecommerce.ecommerce.dao;


import android.util.Log;

import com.iut.ecommerce.ecommerce.fragment.PromotionView;
import com.iut.ecommerce.ecommerce.modele.Promotion;
import com.iut.ecommerce.ecommerce.utils.ActiviteEnAttenteAvecResultat;
import com.iut.ecommerce.ecommerce.utils.RequeteSQLPromotion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Miljold on 09/01/2018.
 */


public class PromotionDao implements Dao<Promotion> {
    private static final String URL =
            "https://infodb.iutmetz.univ-lorraine.fr/~gaiga4u/ecommerce/promotion/";

    private ActiviteEnAttenteAvecResultat activite;

    // Singleton instance
    private static com.iut.ecommerce.ecommerce.dao.PromotionDao mInstance = null;

    public static com.iut.ecommerce.ecommerce.dao.PromotionDao getInstance(ActiviteEnAttenteAvecResultat activite) {
        if (mInstance == null) {
            mInstance = new com.iut.ecommerce.ecommerce.dao.PromotionDao(activite);
        } else {
            if (mInstance.activite != activite) {
                mInstance.activite = activite;
            }
        }
        return mInstance;
    }


    private PromotionDao(ActiviteEnAttenteAvecResultat activite) {
        this.activite = activite;
    }


    @Override
    public void findAll() {
        RequeteSQLPromotion req = new RequeteSQLPromotion(activite, this);
        req.execute(URL + "find.php");
    }


    @Override
    public void create(Promotion promotion) {
        RequeteSQLPromotion req = new RequeteSQLPromotion(activite, this);
        Log.i("create", "Création d'une nouvelle entrée en base");
        String url = URL + "create.php";
        String params = "?id_article_fk=" + promotion.getIdArticle() + "&pourcentage=" + promotion.getPourcentage() + "&date_debut=" + promotion.getDateDebut() + "&date_fin=" + promotion.getDateFin();
        Log.i("_adCreate", params);
        req.execute(url + params);
    }

    @Override
    public void update(Promotion promotion) {
        RequeteSQLPromotion req = new RequeteSQLPromotion(activite, this);
        Log.i("update", "Modification d'une entrée en base");
        String url = URL + "update.php";
        String params = "?id_article_fk=" + promotion.getIdArticle() + "&pourcentage=" + promotion.getPourcentage() + "&date_debut=" + promotion.getDateDebut() + "&date_fin=" + promotion.getDateFin();
        req.execute(url + params);
    }

    @Override
    public void delete(Promotion promotion) {
        RequeteSQLPromotion req = new RequeteSQLPromotion(activite, this);
        Log.i("delete", "Suppression d'une entrée en base");
        String url = URL + "delete.php";
        Log.i("iD", String.valueOf(promotion.getIdArticle()));
        String params = "?id_article_fk=" + promotion.getIdArticle();
        req.execute(url + params);
    }


    public void check() {
        RequeteSQLPromotion req = new RequeteSQLPromotion(activite, this);
        Log.i("check", "Suppression des promotions dépassées");
        String url = URL + "check.php";
        req.execute(url);
    }


    @Override
    public void traiteFindAll(String result) {
        ArrayList<Promotion> liste = new ArrayList<Promotion>();

        try {

            Log.i("_ta", "Traitement du JSON promotion");

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            //df.parse(row.getString("date_debut");

            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject row = array.getJSONObject(i);
                Promotion c = null;

                try {
                    c = new Promotion(row.getInt("id_article_fk"),
                            df.parse(row.getString("date_debut")),
                            df.parse(row.getString("date_fin")),
                            row.optLong("pourcentage"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                liste.add(c);

            }
            Log.i("_ta", liste.get(0).toString());
            Log.i("_ta", "Fin de traitement du JSON promotion");
            this.activite.notifyRetourRequeteFindAll(liste);

        } catch (JSONException je) {
            Log.i("_ta", "ERREUR - Traitement du JSON promotion");
            System.out.println("Pb json : " + je);
        }


    }
}
