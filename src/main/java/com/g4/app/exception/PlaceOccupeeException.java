package com.g4.app.exception;

public class PlaceOccupeeException extends Exception {
    public PlaceOccupeeException() {
        super("la place est déjà occuppée...");
    }
}
