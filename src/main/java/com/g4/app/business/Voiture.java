package com.g4.app.business;

public class Voiture extends Vehicule {

    public Voiture(String imm, String mod, String mar, String prop) {
        super(imm, mod, mar, prop);
    }

    public Voiture() {
        super();
    }

    public boolean isTransporteur() {
        return false;
    }

}
