package com.g4.app.business;

//Interface définissant la stratégie de calcul du tarif
//L'interface est un contrat
public interface FeeStrategy {

    public double calculerCout(Vehicule v);

    public String descritpion();
}
