package com.pharmacy.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmacy.domain.Pharmacy;
import com.pharmacy.domain.PharmacyFilters;
import com.pharmacy.domain.PharmacyRS;
import com.pharmacy.service.PharmacyService;
import com.pharmacy.util.Constants;
import com.pharmacy.wsclient.WSPharmacyClient;

@Component
public class PharmacyImpl implements PharmacyService {

    @Autowired
    private WSPharmacyClient wsClient;

    /**
     * 
     * @param pharmacyFilters
     * @return shiftPharmacies
     * @throws IOException
     */
    public List<Pharmacy> getPharmacy(PharmacyFilters pharmacyFilters) throws IOException {

        String responsePharmacy = wsClient.getPharmacy(pharmacyFilters);
        List<Pharmacy> shiftPharmacies = transformResponseToObject(responsePharmacy);
        
        if(null != pharmacyFilters.getCommune() && null != pharmacyFilters.getPharmacyName()) {
            return completeFilter(pharmacyFilters, shiftPharmacies);
        }else if(null != pharmacyFilters.getCommune()) {
            return filterCommune(pharmacyFilters, shiftPharmacies);
        }else if(null != pharmacyFilters.getPharmacyName()) {
            return filterPharmacyName(pharmacyFilters, shiftPharmacies);
        }

        return shiftPharmacies;
    }
    
    /**
     * 
     * @param pharmacyFilters
     * @param filterListPharmacy
     * @return
     */
    private List<Pharmacy> completeFilter(PharmacyFilters pharmacyFilters, List<Pharmacy> filterListPharmacy){
        List<Pharmacy> listPharmacy = new ArrayList<>();
        for(Pharmacy pharmacy : filterListPharmacy) {
            if(pharmacy.getComunaNombre().equalsIgnoreCase(pharmacyFilters.getCommune()) && 
                    pharmacy.getLocalNombre().equalsIgnoreCase(pharmacyFilters.getPharmacyName())) {
                listPharmacy.add(pharmacy);
            }
        }
        return listPharmacy;
    }
    
    /**
     * 
     * @param pharmacyFilters
     * @param filterListPharmacy
     * @return
     */
    private List<Pharmacy> filterPharmacyName(PharmacyFilters pharmacyFilters, List<Pharmacy> filterListPharmacy){
        List<Pharmacy> listPharmacy = new ArrayList<>();
        for(Pharmacy pharmacy : filterListPharmacy) {
            if(pharmacy.getLocalNombre().equalsIgnoreCase(pharmacyFilters.getPharmacyName())) {
                listPharmacy.add(pharmacy);
            }
        }
        return listPharmacy;
    }
    
    /**
     * 
     * @param pharmacyFilters
     * @param filterListPharmacy
     * @return
     */
    private List<Pharmacy> filterCommune(PharmacyFilters pharmacyFilters, List<Pharmacy> filterListPharmacy){
        List<Pharmacy> listPharmacy = new ArrayList<>();
        for(Pharmacy pharmacy : filterListPharmacy) {
            if(pharmacy.getComunaNombre().equalsIgnoreCase(pharmacyFilters.getCommune())) {
                listPharmacy.add(pharmacy);
            }
        }
        return listPharmacy;
    }

    /**
     * 
     * @param responsePharmacy
     * @return
     * @throws IOException
     */
    private List<Pharmacy> transformResponseToObject(String responsePharmacy)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder s = new StringBuilder();
        s.append(Constants.TRANSFORM_INITIAL_RESPONSE_PHARMACY);
        s.append(responsePharmacy);
        s.append(Constants.TRANSFORM_END_RESPONSE_PHARMACY);
        responsePharmacy = s.toString();
        PharmacyRS rs = mapper.readValue(responsePharmacy, PharmacyRS.class);
        return rs.getPharmacys();

    }

}
