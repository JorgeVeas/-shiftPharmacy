package com.pharmacy.domain;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class PharmacyRS implements Serializable{

    private static final long serialVersionUID = 467028951028018884L;
    
    @Getter @Setter
    private List<Pharmacy> pharmacys;

}
