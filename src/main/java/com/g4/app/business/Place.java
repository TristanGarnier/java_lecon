package com.g4.app.business;

import java.util.Observable;

import com.g4.app.exception.*;

public abstract class Place extends Observable {
    static int nbinstance = 0;
    private int numero;
    private String reservationImmat;
    private boolean reserve = false;

    protected Vehicule vehicule = null;

    public Place() {
        numero = nbinstance++;
    }

    // true si place dispo
    public boolean isFree() {
        return vehicule == null;
    }

    // true si reservation de la place
    public boolean isreserved() {
        return reserve;
    }

    // true si la reservation à étéb faite pour ce véhicule
    public boolean isReserved(String immat) {
        return !immat.equals(reservationImmat) && reserve;
    }

    // renvoyer le véhicule stationné

    public Vehicule getParkedVehicule() {
        return vehicule;
    }

    public void liberer() {
        reserve = false;
        reservationImmat = null;
    }

    public void reserver(String immat) throws PlusAucunePlaceException {
        if (reserve)
            throw (new PlusAucunePlaceException());
        else {
            reservationImmat = immat;
            reserve = true;
        }
    }

    public int getNumero() {
        return numero;
    }

    public abstract boolean isTransporteur();

    public abstract void park(Vehicule v) throws PlaceOccupeeException;

    public void retirer() throws PlaceLibreException {
        if (vehicule == null)
            throw new PlaceLibreException();
        Vehicule tmp = vehicule;
        vehicule = null;
        System.out.println("Noyification de véhicule retiré");
    }
}
