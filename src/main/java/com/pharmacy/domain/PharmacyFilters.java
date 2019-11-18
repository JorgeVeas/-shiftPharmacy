package com.pharmacy.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class PharmacyFilters implements Serializable{
    
    private static final long serialVersionUID = -8410036442713252248L;
    
    @Getter @Setter
    private String commune;
    
    @Getter @Setter
    private String pharmacyName;

}
