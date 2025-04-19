package com.hmsapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {
    private JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //h(cd)^2
        http.csrf().disable().cors().disable();//crsf: cross-site request forgery
        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
        //haap
        http.authorizeHttpRequests().anyRequest().permitAll();
//        http.authorizeHttpRequests()
//                .requestMatchers("/api/auth/sign-up","/api/auth/property/sign-up","/api/auth/login")
//                .permitAll()
//                .requestMatchers("/api/property/addProperty","/api/property/updateProperty/{propertyId}")
//                .hasRole("OWNER")
//                .requestMatchers("/api/property/deletePropertyById/{propertyId}","/api/property/allProperty","/api/property/{propertyId}")
//                .hasAnyRole("OWNER","ADMIN")
//                .requestMatchers("/api/auth/blog/sign-up","/api/city/**","/api/country/**")
//                .hasRole("ADMIN")
//                .anyRequest().authenticated();
        return http.build();
    }
}
