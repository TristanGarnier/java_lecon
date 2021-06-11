package com.g4.app.business;

public class Camion extends Vehicule {
    public Camion(String imm, String mod, String mar, String prop) {
        super(imm, mod, mar, prop);
    }

    public Camion() {
        super();
    }

    public boolean isTransporteur() {
        return true;
    }
}
