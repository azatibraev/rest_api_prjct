package com.example.company_rest_api.security;

import com.example.company_rest_api.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Azat Ibraev
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true)

public class WebAppSecurity {

    private final TokenVerifierFilter tokenVerifierFilter;

    public WebAppSecurity(TokenVerifierFilter tokenVerifierFilter) {
        this.tokenVerifierFilter = tokenVerifierFilter;
    }

    // authentication
    @Bean
    AuthenticationProvider authenticationProvider(UserRepository userRepository) {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService((email) -> userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "user with email = " + email + " not found!"
                )));

        provider.setPasswordEncoder(getPasswordEncoder());

        return provider;
    }

    // authorization
    @Bean
    SecurityFilterChain authorization(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable()
                .authorizeRequests( auth -> auth
                        .antMatchers("/swagg", "/swagger-ui/index.html").permitAll()
                        .anyRequest()
                        .permitAll()
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(tokenVerifierFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
