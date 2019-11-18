package com.pharmacy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PharmacyException extends RuntimeException{
    
    private static final long serialVersionUID = -4344847182112518872L;

    private final String message;
}
