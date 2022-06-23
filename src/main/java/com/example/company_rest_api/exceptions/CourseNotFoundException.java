package com.example.company_rest_api.exceptions;

/**
 * @author Azat Ibraev
 */
public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String message) {
        super(message);
    }
}
