package com.telcom.ups.monolitico.util.response.error;

public class GeneralException extends Exception{

    private static final String DESCRIPCION = "Internal Server Error Exception (500)";

    public GeneralException(String message) {
        super(DESCRIPCION + ": " + message);
    }
}
