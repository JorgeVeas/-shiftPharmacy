package com.pharmacy.service;

import java.io.IOException;
import java.util.List;

import com.pharmacy.domain.Pharmacy;
import com.pharmacy.domain.PharmacyFilters;


public interface PharmacyService {
    
    public List<Pharmacy> getPharmacy(PharmacyFilters pharmacyFilters) throws IOException;

}
