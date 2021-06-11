package com.g4.app.business;

public class Constante {
    public static final int nbPlaceParticulier = 20;
    public static final int nbPlaceTransporteur = 10;
    public final double TVA = 19.6;
    public static FeeStrategy tarif = new BrandFee();
}
