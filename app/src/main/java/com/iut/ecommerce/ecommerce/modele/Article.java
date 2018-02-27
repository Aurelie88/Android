package com.iut.ecommerce.ecommerce.modele;

import java.io.Serializable;

/**
 * Created by Miljold on 09/01/2018.
 */

public class Article implements Serializable{
    private int idArticle;
    private String reference;
    private String nomArticle;
    private float tarif;
    private String visuelArticle;
    private int idCategorie;

    // Article(id_article, reference, nom, tarif, visuel, id_categorie)
    public Article(int idArticle, String reference, String nomArticle, float tarif, String visuelArticle, int idCategorie) {
        this.setIdArticle(idArticle);
        this.setReference(reference);
        this.setNomArticle(nomArticle);
        this.setTarif(tarif);
        this.setVisuelArticle(visuelArticle);
        this.setIdCategorie(idCategorie);
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public float getTarif() {
        return tarif;
    }

    public void setTarif(float tarif) {
        this.tarif = tarif;
    }

    public String getVisuelArticle() {
        return visuelArticle;
    }

    public void setVisuelArticle(String visuelArticle) {
        this.visuelArticle = visuelArticle;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    @Override
    public String toString() {
        return this.getNomArticle();
    }
}
