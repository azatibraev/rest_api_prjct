package com.example.company_rest_api.exceptions;

/**
 * @author Azat Ibraev
 */
public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(String message) {
        super(message);
    }
}
