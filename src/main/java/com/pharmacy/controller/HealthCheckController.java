package com.pharmacy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.util.Constants;

@RestController
@RequestMapping("/healthCheck")
public class HealthCheckController extends BaseController {
    
    @GetMapping(value = Constants.EMPTY_LINE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>(getResponseHeaders(), HttpStatus.OK);
    }
}
