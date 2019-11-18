package com.pharmacy.wsclient;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.pharmacy.config.PharmacyConfig;
import com.pharmacy.domain.PharmacyFilters;

@RunWith(MockitoJUnitRunner.class)
public class WSPharmacyClientTest {

public static final String MUST_BE_A_NOTNULL = "can't be a null value";
    
    @Mock
    private RestTemplate restTemplate;
    
    @InjectMocks
    private PharmacyConfig pharmacyConfig;
    
    @InjectMocks
    private WSPharmacyClient wSPharmacyClient;
    
    @InjectMocks
    private ResponseEntity<String> restResponse = new ResponseEntity<>("[\r\n" + 
            "  {\r\n" + 
            "    \"fecha\": \"15-11-2019\",\r\n" + 
            "    \"local_id\": \"534\",\r\n" + 
            "    \"local_nombre\": \"TORRES MPD\",\r\n" + 
            "    \"comuna_nombre\": \"RECOLETA\",\r\n" + 
            "    \"localidad_nombre\": \"RECOLETA\",\r\n" + 
            "    \"local_direccion\": \"AVENIDA EL SALTO 2972\",\r\n" + 
            "    \"funcionamiento_hora_apertura\": \"10:30 hrs.\",\r\n" + 
            "    \"funcionamiento_hora_cierre\": \"19:30 hrs.\",\r\n" + 
            "    \"local_telefono\": \"+560225053570\",\r\n" + 
            "    \"local_lat\": \"-33.3996351\",\r\n" + 
            "    \"local_lng\": \"-70.62894990000001\",\r\n" + 
            "    \"funcionamiento_dia\": \"viernes\",\r\n" + 
            "    \"fk_region\": \"7\",\r\n" + 
            "    \"fk_comuna\": \"122\"\r\n" + 
            "  },\r\n" + 
            "  {\r\n" + 
            "    \"fecha\": \"15-11-2019\",\r\n" + 
            "    \"local_id\": \"753\",\r\n" + 
            "    \"local_nombre\": \"AHUMADA\",\r\n" + 
            "    \"comuna_nombre\": \"BUIN\",\r\n" + 
            "    \"localidad_nombre\": \"BUIN\",\r\n" + 
            "    \"local_direccion\": \"SAN MARTIN 174\",\r\n" + 
            "    \"funcionamiento_hora_apertura\": \"08:30 hrs.\",\r\n" + 
            "    \"funcionamiento_hora_cierre\": \"22:00 hrs.\",\r\n" + 
            "    \"local_telefono\": \"+560226313086\",\r\n" + 
            "    \"local_lat\": \"-33.732\",\r\n" + 
            "    \"local_lng\": \"-70.735941\",\r\n" + 
            "    \"funcionamiento_dia\": \"viernes\",\r\n" + 
            "    \"fk_region\": \"7\",\r\n" + 
            "    \"fk_comuna\": \"83\"\r\n" + 
            "  }]", HttpStatus.OK);
    
    private byte[] excByte = null;
    
    @Before
    public void before() {
        String exc = "{\"timestamp\":1559008020141,\"status\":409,\"error\":\"Conflict\",\"message\":\"Error.\"}";
        excByte = exc.getBytes();
    }
    
    @Test
    public void testPharmacyClient() throws IOException {
        pharmacyConfig.setEndpoint("farmanet.minsal.cl");
        pharmacyConfig.setProcess("maps/index.php/ws/getLocalesRegion");
        pharmacyConfig.setScheme("https");
        wSPharmacyClient = new WSPharmacyClient(pharmacyConfig);
        PharmacyFilters pharmacyFilters = new PharmacyFilters();
        pharmacyFilters.setCommune("");
        pharmacyFilters.setPharmacyName("");
        String response = wSPharmacyClient.getPharmacy(pharmacyFilters);
        Assert.assertNotNull(MUST_BE_A_NOTNULL, response);
    }
    
    @Test(expected = Exception.class)
    public void testImplementsAnyException() throws IOException {
        HttpClientErrorException ex = new HttpClientErrorException(HttpStatus.ACCEPTED, "ACCEPTED", excByte, null);
        wSPharmacyClient.httpClientErrorExtend(ex);
        Assert.assertNotNull(MUST_BE_A_NOTNULL, "OK");
    }

    @Test(expected = Exception.class)
    public void testImplementsUnauth() throws IOException {
        HttpClientErrorException ex = new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", excByte,
                null);
        wSPharmacyClient.httpClientErrorExtend(ex);
        Assert.assertNotNull(MUST_BE_A_NOTNULL, "UNAUTHORIZED");
    }

    @Test(expected = Exception.class)
    public void testImplementsConflict() throws IOException {

        HttpClientErrorException ex = new HttpClientErrorException(HttpStatus.CONFLICT, "CONFLICT", excByte, null);
        wSPharmacyClient.httpClientErrorExtend(ex);
        Assert.assertNotNull(MUST_BE_A_NOTNULL, "CONFLICT");
    }

}
