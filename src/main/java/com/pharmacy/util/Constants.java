package com.pharmacy.util;

public class Constants {

    public static final String PORT_CONFIG = "port: {}";
    public static final String SCHEME_CONFIG = "scheme: {}";
    public static final String ENDPOINT_CONFIG = "endpoint: {}";
    public static final String PROCESS_CONFIG = "process: {}";
    public static final String ERROR = "Error: ";

    public static final String TYPE_JSON = "application/json";
    
    public static final String API_REQUEST = "API request: {}";
    public static final String API_RESPONSE = "API response: {}";

    public static final String URI = "URI: {}";
    public static final String EMPTY_LINE = "";

    public static final String ERROR_UNAUTHORIZED_CODE = "401 UNAUTHORIZED";
    public static final String ERROR_MESSAGE_UNAUTHORIZED = "HTTP Header (X_AUTH_TOKEN | X_RESOURCE_NAME) not defined.";
    
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_CONTENT_TYPE_VALUE = "multipart/form-data";
    public static final String ERROR_HEADER_CONTENT_TYPE = "The header" + HEADER_CONTENT_TYPE + "it can not be null.";
    public static final String ERROR_HEADER_CONTENT_TYPE_VALUE_NULL = "The header" + HEADER_CONTENT_TYPE + "does not correspond to the requested value.";
    
    public static final String API_OPERATION_PHARMACY = "/pharmaciesInShift";
    public static final String API_OPERATION_COMMUNE = "/commune";
    public static final String SUFFIX = "?id_region=";
    public static final String SUFFIX_VALUE = "7";
    public static final String SUFFIX_COMMUNE = "reg_id";
    
    public static final String SHOOSE_COMMUNE = "Elija Comuna";
    public static final String SEPARATOR = "><";
    public static final String SEPARATOR_REPLACE = ">\n<";
    public static final String LINE_BREAK = "\n";
    
    public static final String TRANSFORM_INITIAL_RESPONSE_PHARMACY = "{\"pharmacys\":";
    public static final String TRANSFORM_END_RESPONSE_PHARMACY= "}";
    
    public static final String RESPONSE = "Response: ";
    
    private Constants() {
    }
}
