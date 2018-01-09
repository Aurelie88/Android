package com.iut.ecommerce.ecommerce.modele;

/**
 * Created by Miljold on 09/01/2018.
 */

public class LigneCommande {
    private int idCommande;
    private int idLigne;
    private int idArticle;

    public LigneCommande(int idCommande, int idLigne, int idArticle) {
        this.setIdCommande(idCommande);
        this.setIdLigne(idLigne);
        this.setIdArticle(idArticle);
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
}
