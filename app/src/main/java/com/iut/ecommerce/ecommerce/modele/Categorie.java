package com.iut.ecommerce.ecommerce.modele;

import java.io.Serializable;

/**
 * Created by Miljold on 09/01/2018.
 */

public class Categorie implements Comparable<Categorie>, Serializable {
    private int idCateg;
    private String nomCateg;
    private String visuelCateg;

   public Categorie(int idCateg, String nomCateg, String visuelCateg){
       this.setIdCateg(idCateg);
       this.setNomCateg(nomCateg);
       this.setVisuelCateg(visuelCateg);
   }

    public Categorie(String nomCateg, String visuelCateg){
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
        this.nomCateg = nomCateg.trim().toUpperCase();
    }

    public String getVisuelCateg() {
        return visuelCateg;
    }

    public void setVisuelCateg(String visuelCateg) {
        this.visuelCateg = visuelCateg;
    }

    /**
     * Méthode nécessaire pour que contains et indexOf de ArrayList fonctionnent
     * Attention : equals(Object) et non equals(Devise)
     */
    @Override
    public boolean equals(Object d) {

        if (d instanceof Categorie) {
            return ((Categorie)d).getNomCateg().equals(this.getNomCateg());
        }

        return false;
    }

    /**
     * Méthode de l'interface Comparable à implementer pour
     * pouvoir utiliser Collections.sort(liste)
     *
     * @param o la devise à comparer
     * @return un entier permettant de trier
     */
    @Override
    public int compareTo(Categorie o) {

        return this.getNomCateg().compareTo(o.getNomCateg());
    }

    @Override
    public String toString() {
        return this.getNomCateg();
    }
}
