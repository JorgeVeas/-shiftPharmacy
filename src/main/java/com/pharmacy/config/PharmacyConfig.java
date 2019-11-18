package com.pharmacy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.pharmacy.util.Constants;

@Configuration
@PropertySource("classpath:/application.properties")
public class PharmacyConfig {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PharmacyConfig.class);

    private String scheme;
    private String endpoint;
    private String process;

    @Autowired
    public PharmacyConfig(@Value("${service.pharmacy.scheme}") String scheme,
            @Value("${service.pharmacy.endpoint}") String endpoint,
            @Value("${service.pharmacy.process}") String process) {

        this.scheme = scheme;
        this.endpoint = endpoint;
        this.process = process;

        LOGGER.debug(Constants.SCHEME_CONFIG, this.scheme);
        LOGGER.debug(Constants.ENDPOINT_CONFIG, this.endpoint);
        LOGGER.debug(Constants.PROCESS_CONFIG, this.process);
    }

    /**
     * @return the Scheme
     */
    public String getScheme() {
        return scheme;
    }

    /**
     * @param the Scheme to set
     */
    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    /**
     * @return the EndPoint
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * @param the EndPoint to set
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * @return the Process
     */
    public String getProcess() {
        return process;
    }

    /**
     * @param the Process to set
     */
    public void setProcess(String process) {
        this.process = process;
    }

}
