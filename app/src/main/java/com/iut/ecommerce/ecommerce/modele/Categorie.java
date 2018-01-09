package com.iut.ecommerce.ecommerce.modele;

/**
 * Created by Miljold on 09/01/2018.
 */

public class Categorie {
    private int idCateg;
    private String nomCateg;
    private String visuelCateg;

   public Categorie(int idCateg, String nomCateg, String visuelCateg){
       this.setIdCateg(idCateg);
       this.setNomCateg(nomCateg);
       this.setVisuelCateg(visuelCateg);
   }

    public int getIdCateg() {
        return idCateg;
    }

    public void setIdCateg(int idCateg) {
        this.idCateg = idCateg;
    }

    public String getNomCateg() {
        return nomCateg;
    }

    public void setNomCateg(String nomCateg) {
        this.nomCateg = nomCateg;
    }

    public String getVisuelCateg() {
        return visuelCateg;
    }

    public void setVisuelCateg(String visuelCateg) {
        this.visuelCateg = visuelCateg;
    }
}
