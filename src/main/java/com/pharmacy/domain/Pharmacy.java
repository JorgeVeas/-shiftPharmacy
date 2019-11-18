package com.pharmacy.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


public class Pharmacy implements Serializable {
    
    private static final long serialVersionUID = -1224414184556466839L;
    
    @Getter @Setter
    private String fecha;
    
    @Getter @Setter
    @JsonProperty("local_id")
    private String localId;
    
    @Getter @Setter
    @JsonProperty("local_nombre")
    private String localNombre;
    
    @Getter @Setter
    @JsonProperty("comuna_nombre")
    private String comunaNombre;
    
    @Getter @Setter
    @JsonProperty("localidad_nombre")
    private String localidadNombre;
    
    @Getter @Setter
    @JsonProperty("local_direccion")
    private String localDireccion;
    
    @Getter @Setter
    @JsonProperty("funcionamiento_hora_apertura")
    private String funcionamientoHoraApertura;
    
    @Getter @Setter
    @JsonProperty("funcionamiento_hora_cierre")
    private String funcionamientoHoraCierre;
    
    @Getter @Setter
    @JsonProperty("local_telefono")
    private String localTelefono;
    
    @Getter @Setter
    @JsonProperty("local_lat")
    private String localLat;
    
    @Getter @Setter
    @JsonProperty("local_lng")
    private String localLng;
    
    @Getter @Setter
    @JsonProperty("funcionamiento_dia")
    private String funcionamientoDia;
    
    @Getter @Setter
    @JsonProperty("fk_region")
    private String fkRegion;
    
    @Getter @Setter
    @JsonProperty("fk_comuna")
    private String fkComuna;

}
