package com.example.company_rest_api.exceptions;

/**
 * @author Azat Ibraev
 */
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
