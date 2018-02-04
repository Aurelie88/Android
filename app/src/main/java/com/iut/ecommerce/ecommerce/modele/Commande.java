package com.iut.ecommerce.ecommerce.modele;

import java.sql.Date;

/**
 * Created by Miljold on 09/01/2018.
 */

public class Commande extends Generique {
    private int idCommande;
    private Date date;
    private int idClient;

    public Commande(int idCommande, Date date, int idClient) {
        this.setIdCommande(idCommande);
        this.setDate(date);
        this.setIdClient(idClient);
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
}
