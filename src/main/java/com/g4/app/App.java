package com.g4.app;

import com.g4.app.business.*;
import com.g4.app.exception.*;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws PlaceOccupeeException, PlaceLibreException {
        System.out.println("Hello World!");
        Voiture voit = new Voiture("AL 392 GE", "Clio Campus.com", "Renault", "Moi");
        Camion cam = new Camion("513 ad 698", "cpassorcier", "FR3", "Jamy");
        Moto moto = new Moto("FG 555 DS", "GP", "moto", "Anto");

        Parking parking = Parking.getInstance();
        parking.park(voit);
        parking.park(moto);
        parking.park(cam);
        // parking.etatParking();
        parking.unpark(20);
        parking.etatParking();
        try {
            parking.park(moto, parking.bookPlace(moto, 1));
        } catch (PlusAucunePlaceException e) {
            System.out.println(e.getMessage());
        }

    }
}
