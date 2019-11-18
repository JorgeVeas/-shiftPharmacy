package com.pharmacy.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class HealthCheckControllerTest {
    
    public static final String MUST_BE_A_NOTNULL = "can't be a null value";

    @InjectMocks
    private HealthCheckController healthCheck = new HealthCheckController();

    @Test
    public void testHealthCheckController() {
        ResponseEntity<String> response = healthCheck.healthCheck();
        Assert.assertNotNull(MUST_BE_A_NOTNULL, response);
    }
}
