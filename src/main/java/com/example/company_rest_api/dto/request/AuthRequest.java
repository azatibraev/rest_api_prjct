package com.example.company_rest_api.dto.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Azat Ibraev
 */
@Getter
@Setter
public class AuthRequest {

    private String email;
    private String password;

}
