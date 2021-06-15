package com.g4.app.business;

//Stratégie le calcule des frais selon la marque de la voiture
public class BrandFee implements FeeStrategy {

    @Override
    public double calculerCout(Vehicule v) {
        if (v.getMarque().equals("BMW")) {
            return 0;
        }
        return 10;
    }

    @Override
    public String descritpion() {
        return "Selon la marque du véhicule";
    }

}
