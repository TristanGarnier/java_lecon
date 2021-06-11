package com.g4.app.business;

public abstract class Vehicule {
    // nos variables de classes
    private String immatriculation;
    private String modele;
    private String marque;
    private String proprietaire;

    // notre constructeur par défaut
    public Vehicule() {
        System.out.println("création de l'objet véhicule (vide)");
    }

    public Vehicule(String imm, String mod, String mar, String prop) {
        immatriculation = imm;
        modele = mod;
        marque = mar;
        proprietaire = prop;
        System.out.println(toString());
    }

    public abstract boolean isTransporteur();

    // génération getter setter
    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    @Override // annotation permet de spécifier la surcharge de notre méthode
    public String toString() {
        return getClass().getSimpleName() + " immatriculé " + immatriculation + " appartenant à " + proprietaire
                + " de la marque " + marque + " ayant pour modème " + modele;
    }

}
