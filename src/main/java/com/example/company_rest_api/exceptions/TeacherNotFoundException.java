package com.example.company_rest_api.exceptions;

/**
 * @author Azat Ibraev
 */
public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(String message) {
        super(message);
    }
}
