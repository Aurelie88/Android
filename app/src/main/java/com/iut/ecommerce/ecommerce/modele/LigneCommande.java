package com.iut.ecommerce.ecommerce.modele;

/**
 * Created by Miljold on 09/01/2018.
 */

public class LigneCommande {
    private int idCommande;
    private int idLigne;
    private int idArticle;
    private int quantite;

    public LigneCommande(int idCommande, int idLigne, int idArticle, int quantite) {
        this.setIdCommande(idCommande);
        this.setIdLigne(idLigne);
        this.setIdArticle(idArticle);
        this.setQuantite(quantite);
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getIdLigne() {
        return idLigne;
    }

    public void setIdLigne(int idLigne) {
        this.idLigne = idLigne;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
