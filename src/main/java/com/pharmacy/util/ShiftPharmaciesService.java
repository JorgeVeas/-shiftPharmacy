package com.pharmacy.util;

import javax.servlet.http.HttpServletRequest;

import com.pharmacy.exception.PharmacyRestException;

public class ShiftPharmaciesService {
    
    private String contentType;
    
    /**
     * 
     * @param request
     */
    public void validateSspRequestHeaders(HttpServletRequest request) {
        this.contentType = request.getHeader(Constants.HEADER_CONTENT_TYPE);
        if(null == this.contentType) {
          throw new PharmacyRestException(Constants.ERROR_HEADER_CONTENT_TYPE);
        } else if(this.contentType.equalsIgnoreCase(Constants.HEADER_CONTENT_TYPE_VALUE)) {
            throw new PharmacyRestException(Constants.ERROR_HEADER_CONTENT_TYPE_VALUE_NULL);
        }        
    }
    
    public String getContentType() {
        return contentType;
    }

}
