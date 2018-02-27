package com.iut.ecommerce.ecommerce.modele;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Miljold on 09/01/2018.
 */

public class Promotion implements Serializable{
    private int idArticle;
    private Date dateDebut;
    private Date dateFin;
    private float pourcentage;

    // Promotion(id_article, date_debut, date_fin, pourcentage)
    public Promotion(int idArticle, Date dateDebut, Date dateFin, float pourcentage) {
        this.setIdArticle(idArticle);
        this.setDateDebut(dateDebut);
        this.setDateFin(dateFin);
        this.setPourcentage(pourcentage);
    }

    @Override
    public String toString() {
        return "-"+String.valueOf(pourcentage)+"%";
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
            this.dateDebut = dateDebut;
        }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public float getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(float pourcentage) {
        this.pourcentage = pourcentage;
    }
}
