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

import com.pharmacy.domain.Commune;
import com.pharmacy.service.CommuneService;
import com.pharmacy.util.Constants;

@RunWith(MockitoJUnitRunner.class)
public class CommuneControllerTest {
    
    public static final String MUST_BE_A_NOTNULL = "can't be a null value";
    
    @Mock
    private CommuneService communeService;
    
    @InjectMocks
    CommuneController communeController;
    
    private List<Commune> listCommune = new ArrayList<>();
    private HttpServletRequest request = Mockito.mock(HttpServletRequest.class); 
    
    @Before
    public void before() {
        Commune commune = new Commune();
        commune.setName("PUENTE ALTO");
        listCommune.add(commune);
        Mockito.when(request.getHeader(Constants.HEADER_CONTENT_TYPE)).thenReturn(Constants.TYPE_JSON);
        
    }
    
    @Test
    public void testCommuneController() throws IOException {
        Mockito.when(communeService.getCommmune(Mockito.any())).thenReturn(listCommune);
        String idRegion = "7";
        ResponseEntity<List<Commune>> resp = communeController.getCommune(request, idRegion);
        Assert.assertNotNull(MUST_BE_A_NOTNULL, resp);
    }
    
    @Test
    public void testCommuneControllerNoContent() throws IOException {
        List<Commune> listCommuneEmty = new ArrayList<>();
        Mockito.when(communeService.getCommmune(Mockito.any())).thenReturn(listCommuneEmty);
        String idRegion = "7";
        ResponseEntity<List<Commune>> resp = communeController.getCommune(request, idRegion);
        Assert.assertNotNull(MUST_BE_A_NOTNULL, resp);
    }
    
    @Test
    public void testCommuneControllerNull() throws IOException {
        List<Commune> listCommuneEmty = null;
        Mockito.when(communeService.getCommmune(Mockito.any())).thenReturn(listCommuneEmty);
        String idRegion = "7";
        ResponseEntity<List<Commune>> resp = communeController.getCommune(request, idRegion);
        Assert.assertNotNull(MUST_BE_A_NOTNULL, resp);
    }

}
