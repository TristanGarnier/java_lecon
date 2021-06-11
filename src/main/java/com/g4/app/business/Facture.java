package com.g4.app.business;

import java.util.List;

public class Facture {
    private static int numFactures = 0;
    private static List<Facture> factures = load();
    private int numFacture = ++numFactures;
    private FeeStrategy tarif = Constante.tarif;
    private Vehicule vehicule;

    public static List<Facture> getFactures() {
        return factures;
    }

    public Facture(Vehicule v) {
        vehicule = v;
        factures.add(this);
    }

    // TODO fin le 30/04/2021 17h10

    private static List<Facture> load() {
        return null;
    }
}
