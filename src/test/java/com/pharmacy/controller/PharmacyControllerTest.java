package com.pharmacy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.pharmacy.domain.Pharmacy;
import com.pharmacy.domain.PharmacyFilters;
import com.pharmacy.service.PharmacyService;
import com.pharmacy.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class PharmacyControllerTest {

    public static final String MUST_BE_A_NOTNULL = "can't be a null value";
    
    @Mock
    private PharmacyService pharmacyService;
    
    @InjectMocks
    PharmacyController pharmacyController;
    
    private List<Pharmacy> listPharmacy = new ArrayList<>();
    private HttpServletRequest request = Mockito.mock(HttpServletRequest.class); 
    
    @Before
    public void before() {
        Pharmacy pharmacy = new Pharmacy(); 
        pharmacy.setComunaNombre("Puente Alto");
        listPharmacy.add(pharmacy);
        Mockito.when(request.getHeader(Constants.HEADER_CONTENT_TYPE)).thenReturn(Constants.TYPE_JSON);
    }
    
    @Test
    public void testPharmacyController() throws IOException {
        PharmacyFilters pharmacyFilters = new PharmacyFilters();
        Mockito.when(pharmacyService.getPharmacy(Mockito.any())).thenReturn(listPharmacy);
        ResponseEntity<List<Pharmacy>> resp = pharmacyController.getPharmacy(request, pharmacyFilters);
        Assert.assertNotNull(MUST_BE_A_NOTNULL, resp);
    }
    
    @Test
    public void testPharmacyControllerNoContent() throws IOException {
        List<Pharmacy> pharmacies = new ArrayList<>();
        PharmacyFilters pharmacyFilters = new PharmacyFilters();
        Mockito.when(pharmacyService.getPharmacy(Mockito.any())).thenReturn(pharmacies);
        ResponseEntity<List<Pharmacy>> resp = pharmacyController.getPharmacy(request, pharmacyFilters);
        Assert.assertNotNull(MUST_BE_A_NOTNULL, resp);
    }
    
    @Test
    public void testPharmacyControllerNull() throws IOException {
        List<Pharmacy> pharmacies = null;
        PharmacyFilters pharmacyFilters = new PharmacyFilters();
        Mockito.when(pharmacyService.getPharmacy(Mockito.any())).thenReturn(pharmacies);
        ResponseEntity<List<Pharmacy>> resp = pharmacyController.getPharmacy(request, pharmacyFilters);
        Assert.assertNotNull(MUST_BE_A_NOTNULL, resp);
    }
}
