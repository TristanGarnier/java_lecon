package com.g4.app.exception;

public class PlusAucunePlaceException extends Exception {
    public PlusAucunePlaceException() {
        super("la place n'est pas disponible");
    }
}
