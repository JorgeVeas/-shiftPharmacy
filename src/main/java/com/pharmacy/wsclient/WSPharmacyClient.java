package com.pharmacy.wsclient;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmacy.config.PharmacyConfig;
import com.pharmacy.domain.ErrorResponse;
import com.pharmacy.domain.PharmacyFilters;
import com.pharmacy.exception.PharmacyException;
import com.pharmacy.exception.PharmacyRestException;
import com.pharmacy.util.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WSPharmacyClient {

    private PharmacyConfig pharmacyConfig;
    
    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public WSPharmacyClient(PharmacyConfig pharmacyConfig) {
        this.pharmacyConfig = pharmacyConfig;
    }

    /**
     * 
     * @param pharmacyFilters
     * @return
     * @throws IOException
     */
    public String getPharmacy(PharmacyFilters pharmacyFilters) throws IOException {

        log.debug(Constants.API_REQUEST, pharmacyFilters);
        String build = null;
        String response = null;

        try {
            UriComponentsBuilder builder = UriComponentsBuilder.newInstance().scheme(pharmacyConfig.getScheme())
                    .host(pharmacyConfig.getEndpoint()).path(pharmacyConfig.getProcess());

            build = builder.toUriString();

            build += Constants.SUFFIX + Constants.SUFFIX_VALUE;
            log.info(Constants.URI, build);

            ResponseEntity<String> restResponse = restTemplate.getForEntity(build, String.class);

            log.info(Constants.RESPONSE + restResponse.getStatusCode());
            if (restResponse.getStatusCode() == HttpStatus.OK) {
                response = restResponse.getBody();
            }
        } catch (HttpClientErrorException e) {
            httpClientErrorExtend(e);
        }
        return response;
    }

    /**
     * 
     * @param e
     * @throws IOException
     */
    public void httpClientErrorExtend(HttpClientErrorException e) throws IOException {
        log.info(Constants.ERROR + e.getResponseBodyAsString());
        ObjectMapper mapper = new ObjectMapper();
        ErrorResponse result = new ErrorResponse();
        String responseString = null;
        if (!(e.getStatusCode().toString()).equals(Constants.ERROR_UNAUTHORIZED_CODE)) {
            responseString = e.getResponseBodyAsString();
            result = mapper.readValue(responseString, ErrorResponse.class);
        }
        if ((e.getStatusCode().toString()).equals((HttpStatus.CONFLICT).toString())) {
            throw new PharmacyException(result.getMessage());
        } else if ((e.getStatusCode().toString()).equals((HttpStatus.UNAUTHORIZED).toString())) {
            throw new PharmacyRestException(Constants.ERROR_MESSAGE_UNAUTHORIZED);
        } else {
            throw new PharmacyException(result.getMessage());
        }
    }

}
