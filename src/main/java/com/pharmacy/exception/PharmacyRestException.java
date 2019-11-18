package com.pharmacy.exception;

public class PharmacyRestException extends RuntimeException{

    private static final long serialVersionUID = -4510760642376200569L;
    
    public PharmacyRestException(String message) {
        super(message);
    }

}
