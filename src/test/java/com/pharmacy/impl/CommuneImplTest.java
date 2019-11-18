package com.pharmacy.impl;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.pharmacy.domain.Commune;
import com.pharmacy.service.impl.CommuneImpl;
import com.pharmacy.wsclient.WSCommuneClient;

@RunWith(MockitoJUnitRunner.class)
public class CommuneImplTest {

    public static final String MUST_BE_A_NOTNULL = "can't be a null value";
    
    @Mock
    private WSCommuneClient wSCommuneClient;
    
    @InjectMocks
    private CommuneImpl communeImpl;
    
    @Test
    public void testCommuneImpl() throws IOException {
        String response = "<option value='0' selected>Elija Comuna</option><option value='0' selected>Elija Comuna</option><option value='82'>ALHUE</option><option value='83'>BUIN</option>";
        String idRegion = "7";
        Mockito.when(wSCommuneClient.getCommune(idRegion)).thenReturn(response);
        List<Commune> listCommune = communeImpl.getCommmune(idRegion);
        Assert.assertNotNull(MUST_BE_A_NOTNULL, listCommune);
    }
}
