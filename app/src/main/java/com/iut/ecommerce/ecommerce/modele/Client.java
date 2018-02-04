package com.iut.ecommerce.ecommerce.modele;

/**
 * Created by Miljold on 09/01/2018.
 */

public class Client extends Generique {
    private int idClient;
    private String nom;
    private String prenom;
    private String ville;

    public Client(int idClient, String nom, String prenom, String ville) {
        this.setIdClient(idClient);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setVille(ville);
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
