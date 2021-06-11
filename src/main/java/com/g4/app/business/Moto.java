package com.g4.app.business;

public class Moto extends Vehicule {
    public Moto(String imm, String mod, String mar, String prop) {
        super(imm, mod, mar, prop);
    }

    public Moto() {
        super();
    }

    public boolean isTransporteur() {
        return false;
    }
}
