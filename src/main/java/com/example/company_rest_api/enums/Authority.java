package com.example.company_rest_api.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Azat Ibraev
 */
public enum Authority implements GrantedAuthority {
    ADMIN,
    STUDENT,
    TEACHER;

    @Override
    public String getAuthority() {
        return name();
    }
}
