package com.pharmacy.wsclient;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.pharmacy.config.CommuneConfig;

@RunWith(MockitoJUnitRunner.class)
public class WSCommuneClientTest {
    
    public static final String MUST_BE_A_NOTNULL = "can't be a null value";
    
    @Mock
    private RestTemplate restTemplate;
    
    @InjectMocks
    private CommuneConfig communeConfig;
    
    @InjectMocks
    private WSCommuneClient wSCommuneClient;
    
    @InjectMocks
    private ResponseEntity<String> restResponse = new ResponseEntity<>("<option value='0' selected>Elija Comuna</option><option value='82'>ALHUE</option><option value='83'>BUIN</option>", HttpStatus.OK);
    
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    
    private byte[] excByte = null;
    
    @Before
    public void before() {
        String exc = "{\"timestamp\":1559008020141,\"status\":409,\"error\":\"Conflict\",\"message\":\"Error.\"}";
        excByte = exc.getBytes();
    }
    
    @Test
    public void testWSCommuneClient() throws IOException {
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        
        HttpEntity<MultiValueMap<String, String>> imageEntity = new HttpEntity<>(map, headers);
        
        Mockito.when(restTemplate.exchange("", HttpMethod.POST, imageEntity, String.class)).thenReturn(restResponse);
        communeConfig.setEndpoint("midastest.minsal.cl");
        communeConfig.setProcess("farmacias/maps/index.php/utilidades/maps_obtener_comunas_por_regiones");
        communeConfig.setScheme("https");
        wSCommuneClient = new WSCommuneClient(communeConfig);
        String idRegion = "7";
        String response = wSCommuneClient.getCommune(idRegion);
        Assert.assertNotNull(MUST_BE_A_NOTNULL, response);
    }
    
    @Test(expected = Exception.class)
    public void testImplementsAnyException() throws IOException {
        HttpClientErrorException ex = new HttpClientErrorException(HttpStatus.ACCEPTED, "ACCEPTED", excByte, null);
        wSCommuneClient.httpClientErrorExtend(ex);
        Assert.assertNotNull(MUST_BE_A_NOTNULL, "OK");
    }

    @Test(expected = Exception.class)
    public void testImplementsUnauth() throws IOException {
        HttpClientErrorException ex = new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", excByte,
                null);
        wSCommuneClient.httpClientErrorExtend(ex);
        Assert.assertNotNull(MUST_BE_A_NOTNULL, "UNAUTHORIZED");
    }

    @Test(expected = Exception.class)
    public void testImplementsConflict() throws IOException {

        HttpClientErrorException ex = new HttpClientErrorException(HttpStatus.CONFLICT, "CONFLICT", excByte, null);
        wSCommuneClient.httpClientErrorExtend(ex);
        Assert.assertNotNull(MUST_BE_A_NOTNULL, "CONFLICT");
    }

}
