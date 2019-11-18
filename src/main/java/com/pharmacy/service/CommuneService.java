package com.pharmacy.service;

import java.io.IOException;
import java.util.List;

import com.pharmacy.domain.Commune;

public interface CommuneService {

    public List<Commune> getCommmune(String idRegion) throws IOException;
        
}
