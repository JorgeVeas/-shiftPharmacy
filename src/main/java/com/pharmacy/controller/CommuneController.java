package com.pharmacy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.domain.Commune;
import com.pharmacy.service.CommuneService;
import com.pharmacy.util.Constants;
import com.pharmacy.util.ShiftPharmaciesService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/information")
public class CommuneController extends BaseController{
    
    private CommuneService communeService;
    
    @Autowired
    public CommuneController(CommuneService communeService) {
        this.communeService = communeService;
    }
    
    /**
     * 
     * @param request
     * @param idRegion
     * @return
     * @throws IOException
     */
    @GetMapping(value = Constants.API_OPERATION_COMMUNE, produces = Constants.TYPE_JSON)
    public ResponseEntity<List<Commune>> getCommune(HttpServletRequest request,
            @Valid @RequestParam String idRegion) throws IOException {
        log.info(Constants.API_REQUEST, idRegion);
        ShiftPharmaciesService ls = null;
        ls = new ShiftPharmaciesService();
        ls.validateSspRequestHeaders(request);
        List<Commune> listCommune = communeService.getCommmune(idRegion);
        if (listCommune != null && !listCommune.isEmpty()) {
            return new ResponseEntity<>(listCommune, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

}
