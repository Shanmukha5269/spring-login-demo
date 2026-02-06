package com.example.login.component;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TokenStore {

    private final Map<String,String> tokens = new HashMap<>();

    public void store(String token, String username){
        tokens.put(token,username);
    }

    public boolean isValid(String token){
        return tokens.containsKey(token);
    }

    public String getUsername(String token){
        return tokens.get(token);
    }

}
