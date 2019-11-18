package com.pharmacy.domain;

import lombok.Data;

@Data
public class ErrorResponse {

    private long timestamp;
    private int status;
    private String error;
    private String message;

}
