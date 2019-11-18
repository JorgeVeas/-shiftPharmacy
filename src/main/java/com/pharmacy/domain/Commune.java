package com.pharmacy.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class Commune implements Serializable{

    private static final long serialVersionUID = 2960966413334346842L;
    
    @Getter @Setter
    private String name;
}
