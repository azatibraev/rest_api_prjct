package com.example.company_rest_api.apis;

import com.example.company_rest_api.dto.request.AuthRequest;
import com.example.company_rest_api.dto.request.AuthResponse;
import com.example.company_rest_api.services.AuthService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Azat Ibraev
 */
@RestController
@RequestMapping("/api/auth")
public class AuthApi {

    private final AuthService authService;

    public AuthApi(AuthService authService) {
        this.authService = authService;
    }

    //generate token
    @PostMapping("/authenticate")
    @PreAuthorize("permitAll()")
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest);
    }
}
