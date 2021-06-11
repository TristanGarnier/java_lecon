package com.g4.app.business;

import com.g4.app.exception.PlaceOccupeeException;

public class PlaceParticulier extends Place {
    @Override
    public boolean isTransporteur() {
        return false;
    }

    @Override
    public void park(Vehicule v) throws PlaceOccupeeException {
        if (v.isTransporteur() || isReserved(v.getImmatriculation()) || !this.isFree())
            throw new PlaceOccupeeException();
        else {
            vehicule = v;
        }
    }
}
