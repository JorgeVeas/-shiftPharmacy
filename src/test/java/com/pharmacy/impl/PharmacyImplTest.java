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

import com.pharmacy.domain.Pharmacy;
import com.pharmacy.domain.PharmacyFilters;
import com.pharmacy.service.impl.PharmacyImpl;
import com.pharmacy.wsclient.WSPharmacyClient;

@RunWith(MockitoJUnitRunner.class)
public class PharmacyImplTest {
    
    public static final String MUST_BE_A_NOTNULL = "can't be a null value";
    
    @Mock
    private WSPharmacyClient wSPharmacyClient;
    
    @InjectMocks
    private PharmacyImpl pharmacyImpl;
    
    PharmacyFilters pharmacyFilters = new PharmacyFilters();
    
    @Test
    public void testPharmacyImplUnfiltered() throws IOException {
        String response = "[\r\n" + 
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
                "  }]";
        Mockito.when(wSPharmacyClient.getPharmacy(Mockito.any())).thenReturn(response);
        List<Pharmacy> listPharmacy = pharmacyImpl.getPharmacy(pharmacyFilters);
        Assert.assertNotNull(MUST_BE_A_NOTNULL, listPharmacy);
    }
    
    @Test
    public void testPharmacyImplfilteredCommune() throws IOException {
        String response = "[\r\n" + 
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
                "  }]";
        Mockito.when(wSPharmacyClient.getPharmacy(Mockito.any())).thenReturn(response);
        pharmacyFilters.setCommune("BUIN");
        List<Pharmacy> listPharmacy = pharmacyImpl.getPharmacy(pharmacyFilters);
        Assert.assertNotNull(MUST_BE_A_NOTNULL, listPharmacy);
    }
    
    @Test
    public void testPharmacyImplfilteredPharmacyName() throws IOException {
        String response = "[\r\n" + 
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
                "  }]";
        Mockito.when(wSPharmacyClient.getPharmacy(Mockito.any())).thenReturn(response);
        pharmacyFilters.setPharmacyName("AHUMADA");
        List<Pharmacy> listPharmacy = pharmacyImpl.getPharmacy(pharmacyFilters);
        Assert.assertNotNull(MUST_BE_A_NOTNULL, listPharmacy);
    }
    
    @Test
    public void testPharmacyImplfilteredComplete() throws IOException {
        String response = "[\r\n" + 
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
                "  }]";
        Mockito.when(wSPharmacyClient.getPharmacy(Mockito.any())).thenReturn(response);
        pharmacyFilters.setPharmacyName("AHUMADA");
        pharmacyFilters.setCommune("BUIN");
        List<Pharmacy> listPharmacy = pharmacyImpl.getPharmacy(pharmacyFilters);
        Assert.assertNotNull(MUST_BE_A_NOTNULL, listPharmacy);
    }

}
