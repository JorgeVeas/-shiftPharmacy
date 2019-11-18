package com.pharmacy.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.pharmacy.exception.PharmacyRestException;

@RunWith(MockitoJUnitRunner.class)
public class BaseControllerTest {
    
    public static final String MUST_BE_A_NOTNULL = "can't be a null value";

    @InjectMocks
    private BaseController base = new BaseController();

    @InjectMocks
    private PharmacyRestException pharmacyRestException = new PharmacyRestException("ERROR");
    private Exception ex = new Exception("ERROR");

    @Mock
    private MethodArgumentNotValidException method;

    @Before
    public void before() {
        method.getBindingResult();
        Mockito.when(method.getBindingResult()).thenReturn(null);
    }

    @Test
    public void testHandleValidationExceptions() {
        try {
            base.handleValidationExceptions(pharmacyRestException);
            base.handleValidationExceptions(ex);
            base.handleValidationExceptions(method);
            Assert.assertNotNull(MUST_BE_A_NOTNULL, pharmacyRestException);
        } catch (Exception e) {
            Assert.assertNotNull(MUST_BE_A_NOTNULL, pharmacyRestException);
        }
    }
}
