package com.g4.app.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observer;
import java.util.Stack;

import com.g4.app.exception.PasAssezObservateurException;
import com.g4.app.exception.PlaceLibreException;
import com.g4.app.exception.PlaceOccupeeException;
import com.g4.app.exception.PlusAucunePlaceException;

public class Parking {
    private static Parking instance;
    private List<Place> places;

    public static Parking getInstance() {
        if (instance == null)
            instance = new Parking();
        return instance;
    }

    protected Parking() {
        places = new ArrayList<Place>();
        for (int i = 0; i < Constante.nbPlaceParticulier; i++) {
            places.add(new PlaceParticulier());
        }
        for (int i = 0; i < Constante.nbPlaceTransporteur; i++) {
            places.add(new PLaceTransporteur());
        }
    }

    public boolean vehiculeExist(Vehicule v) {
        return places.contains(v);
    }

    // placer un vehicule dans le parking à la premier place trouverr

    public void park(Vehicule v) throws PlaceOccupeeException {
        for (Place place : places) {
            try {
                place.park(v);
                return;
            } catch (PlaceOccupeeException e) {
                System.out.println(e.getMessage());
            }
        }
        throw new PlaceOccupeeException();
    }

    public void park(Vehicule v, int place) throws PlaceOccupeeException {
        park(v, places.get(place));
    }

    public void park(Vehicule v, Place place) throws PlaceOccupeeException {
        if (!place.isReserved(v.getImmatriculation()) && place.isFree()) {
            place.park(v);
        } else
            throw new PlaceOccupeeException();
    }

    // retire un véhicule de la place

    public Vehicule unpark(int place) throws PlaceLibreException, PlaceOccupeeException {
        return unpark(places.get(place));
    }

    public Vehicule unpark(Place place) throws PlaceLibreException, PlaceOccupeeException {
        Vehicule v = place.getParkedVehicule();
        if (v == null) {
            throw new PlaceLibreException();
        }
        place.retirer();
        reorganiserPlace();
        return v;
    }

    public void etatParking() {
        for (int i = 0; i < places.size(); i++) {
            System.out.println("Place : " + i + " : ");
            if (places.get(i).isFree()) {
                if (places.get(i).isreserved()) {
                    System.out.println("Place reservée");
                } else {
                    System.out.println("Place libre");
                }
            } else {
                System.out.println("Place occupée");
            }
        }
    }

    // essayer de réserver la place pour un véhicule donnée à un numéro donnée
    public Place bookPlace(Vehicule v, Place p) throws PlusAucunePlaceException {
        if (!p.isreserved() && p.isFree()) {
            p.reserver((v.getImmatriculation()));
            return p;
        }
        throw new PlusAucunePlaceException();
    }

    public Place bookPlace(Vehicule v, int emplacement) throws PlusAucunePlaceException {
        return bookPlace(v, places.get(emplacement));
    }

    // annule la réservation de la place donnée

    public void freePlace(Place p) throws PlaceLibreException {
        if (!p.isreserved()) {
            throw new PlaceLibreException();
        } else {
            p.liberer();
        }
    }

    public void freePlace(int i) throws PlaceLibreException {
        freePlace(places.get(i));
    }

    // position de la voiture ave un parametre son immatriculation

    public int getLocalisation(String immat) throws PlaceLibreException {
        for (int i = 0; i < places.size(); i++) {
            if (!(places.get(i).isFree()) && places.get(i).getParkedVehicule().getImmatriculation().equals(immat)) {
                return i;
            }
        }
        return -1;
    }

    // retirer du parking selon l'immatriculation

    public Vehicule retirerVehicule(String immat) throws PlaceLibreException, PlaceOccupeeException {
        return unpark(getLocalisation(immat));
    }

    // réorganiser les places suite aux changement et à la libération
    public void reorganiserPlace() throws PlaceLibreException, PlaceOccupeeException {
        Stack<Place> placeLibres = new Stack<Place>();
        for (Place p : places.subList(0, Constante.nbPlaceParticulier)) {
            if (!p.isTransporteur() && p.isFree() && !p.isreserved()) {
                placeLibres.push(p);
            }
        }
        for (Place p : places.subList(Constante.nbPlaceParticulier,
                Constante.nbPlaceParticulier + Constante.nbPlaceTransporteur)) {
            if (!p.isFree() && !p.getParkedVehicule().isTransporteur()) {
                park(unpark(places.get(p.getNumero())), placeLibres.pop());
            }
        }
    }

    // ajouter des observateur dur toute les places, la liste contenir doit autant
    // d'observateur que de place à observcer
    public void observerPlace(List<? extends Observer> observer) throws PasAssezObservateurException {
        if (observer.size() != places.size()) {
            throw new PasAssezObservateurException();
        }
        Iterator<Place> placeIt = places.iterator();
        Iterator<? extends Observer> obsIt = observer.iterator();
        while (placeIt.hasNext() && obsIt.hasNext()) {
            // todo ajouter les observateur aux bouton
            Place p = placeIt.next();
        }
    }

}
