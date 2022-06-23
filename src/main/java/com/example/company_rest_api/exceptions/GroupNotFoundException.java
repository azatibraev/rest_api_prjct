package com.example.company_rest_api.exceptions;

/**
 * @author Azat Ibraev
 */
public class GroupNotFoundException extends RuntimeException {
    public GroupNotFoundException(String message) {
        super(message);
    }
}
