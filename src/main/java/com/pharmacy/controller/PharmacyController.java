package com.pharmacy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.domain.Pharmacy;
import com.pharmacy.domain.PharmacyFilters;
import com.pharmacy.service.PharmacyService;
import com.pharmacy.util.Constants;
import com.pharmacy.util.ShiftPharmaciesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/information")
public class PharmacyController extends BaseController{
    
    private PharmacyService service;

    @Autowired
    public PharmacyController(PharmacyService service) {
        this.service = service;
    }
    
    /**
     * 
     * @param request
     * @param pharmacyFilters
     * @return
     * @throws IOException
     */
    @PostMapping(value = Constants.API_OPERATION_PHARMACY, produces = Constants.TYPE_JSON)
    public ResponseEntity<List<Pharmacy>> getPharmacy(HttpServletRequest request,
            @Valid @RequestBody PharmacyFilters pharmacyFilters) throws IOException {
        log.info(Constants.API_REQUEST, pharmacyFilters);
        ShiftPharmaciesService ls = null;
        ls = new ShiftPharmaciesService();
        ls.validateSspRequestHeaders(request);
        List<Pharmacy> shiftPharmacies = service.getPharmacy(pharmacyFilters);
        if (shiftPharmacies != null && !shiftPharmacies.isEmpty()) {
            return new ResponseEntity<>(shiftPharmacies, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

}
