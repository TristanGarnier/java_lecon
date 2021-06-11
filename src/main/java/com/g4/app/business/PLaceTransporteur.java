package com.g4.app.business;

import com.g4.app.exception.PlaceOccupeeException;

public class PLaceTransporteur extends Place {

    @Override
    public boolean isTransporteur() {
        return true;
    }

    @Override
    public void park(Vehicule v) throws PlaceOccupeeException {
        if (isReserved(v.getImmatriculation()) || !this.isFree())
            throw new PlaceOccupeeException();
        else {
            vehicule = v;
        }
    }

}
