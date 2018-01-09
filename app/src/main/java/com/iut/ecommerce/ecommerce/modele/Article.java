package com.iut.ecommerce.ecommerce.modele;

/**
 * Created by Miljold on 09/01/2018.
 */

public class Article {
    private int idArticle;
    private int reference;
    private String nomArticle;
    private float tarif;
    private String visuelArticle;

    public Article(int idArticle, int reference, String nomArticle, float tarif, String visuelArticle) {
        this.setIdArticle(idArticle);
        this.setReference(reference);
        this.setNomArticle(nomArticle);
        this.setTarif(tarif);
        this.setVisuelArticle(visuelArticle);
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
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
}
