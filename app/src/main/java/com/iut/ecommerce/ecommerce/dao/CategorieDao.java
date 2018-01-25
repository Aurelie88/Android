package com.iut.ecommerce.ecommerce.dao;

import com.iut.ecommerce.ecommerce.modele.Categorie;
import com.iut.ecommerce.ecommerce.vue.CategorieView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Miljold on 09/01/2018.
 */

public class CategorieDao implements Dao<Categorie> {
    private static final String URL =
            "https://infodb.iutmetz.univ-lorraine.fr/~laroche5/ppo/ecommerce/php/categorie/";

    private ActiviteEnAttenteAvecResultat activite;

    private CategorieDao(ActiviteEnAttenteAvecResultat activite) {
        this.activite = activite;
    }


    @Override
    public void findAll() {
        RequeteSQL req = new RequeteSQL(activite, this);
        req.execute(URL + "findall.php");
    }

    @Override
    public void create() {

        //RequeteSQL req = new RequeteSQL(activite, this);
        //String url = URL + "updateCategorie.php";
        //String params = "?nom="+objet.getNom()+"&visuel="+objet.getVisuel();
        //req.execute(url+params);


    }

    @Override
    public void update() {


    }

    @Override
    public void delete() {

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

    // Singleton instance
    private static CategorieDao mInstance = null;

    public static CategorieDao getInstance(CategorieView categorieView) {
        if(mInstance == null)
        {
            mInstance = new CategorieDao(categorieView);
        }
        return getMyObject();
    }

    // Getter to access Singleton instance
    public static CategorieDao getMyObject() {
        return mInstance ;
    }
}



// VERSION SQLite
/*
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
}*/
