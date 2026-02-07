package com.example.login.service;

import com.example.login.component.TokenStore;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TokenService {

    private final TokenStore tokenStore;

    public TokenService(TokenStore tokenStore){
        this.tokenStore = tokenStore;
    }

    public String generateToken(String username){
        String token = UUID.randomUUID().toString();
        tokenStore.store(token,username);
        return token;
    }

    public boolean isValid(String token){
        return tokenStore.isValid(token);
    }

    public String getUsername(String token){
        return tokenStore.getUsername(token);
    }
}
