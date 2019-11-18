package com.pharmacy.wsclient;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pharmacy.config.CommuneConfig;
import com.pharmacy.domain.ErrorResponse;
import com.pharmacy.exception.PharmacyException;
import com.pharmacy.exception.PharmacyRestException;
import com.pharmacy.util.Constants;
import org.springframework.http.HttpMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WSCommuneClient {
    
    private CommuneConfig communeConfig;

    @Autowired
    public WSCommuneClient(CommuneConfig communeConfig) {
        this.communeConfig = communeConfig;
    }
    
    private RestTemplate restTemplate = new RestTemplate();
    
    /**
     * 
     * @param request
     * @return String
     * @throws IOException
     */
    public String getCommune(String request) throws IOException {

        log.debug(Constants.API_REQUEST, request);
        String response = null;
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(Constants.SUFFIX_COMMUNE, request);
        
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.newInstance().scheme(communeConfig.getScheme())
                    .host(communeConfig.getEndpoint()).path(communeConfig.getProcess());
            String build = builder.toUriString();
            log.info(Constants.URI, build);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
           
            HttpEntity<MultiValueMap<String, String>> imageEntity = new HttpEntity<>(map, headers);
            
            ResponseEntity<String> restResponse = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, imageEntity, String.class);          
           
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
