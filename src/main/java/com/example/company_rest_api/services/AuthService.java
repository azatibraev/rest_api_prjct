package com.example.company_rest_api.services;

import com.example.company_rest_api.dto.request.AuthRequest;
import com.example.company_rest_api.dto.request.AuthResponse;
import com.example.company_rest_api.models.User;
import com.example.company_rest_api.repositories.UserRepository;
import com.example.company_rest_api.security.JwtUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Azat Ibraev
 */
@Service
public class AuthService {

    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    public AuthService(JwtUtils jwtUtils, UserRepository userRepository) {
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
    }

    public AuthResponse authenticate(AuthRequest authRequest) {

        User user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "user with email = " + authRequest.getEmail() + " not found!"
                ));

        if (!user.getPassword().equals(authRequest.getPassword())) {
            throw new BadCredentialsException(
                    "invalid password"
            );
        }

        String jwt = jwtUtils.generateJwt(user);

        return new AuthResponse(
                user.getEmail(),
                jwt
        );
    }
}
