package com.hmsapp.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JWTService {
    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private int expiry;

    public Algorithm algorithm;

    @PostConstruct  //this annotation is automatically run when the application started no need to call it
    public void postConstruct() throws UnsupportedEncodingException{
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public String generateToken(String username) {
        return JWT.create()
                .withClaim("name", username)
                .withExpiresAt(new Date(System.currentTimeMillis()+expiry))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String getUsername(String token) {
        DecodedJWT decodedToken = JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token);
        return decodedToken.getClaim("name").asString();
    }

}
