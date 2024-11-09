package com.example.demo.domain.entidadesApi;



import jakarta.servlet.http.Cookie;
import lombok.Getter;

@Getter
public class LoginResponse {
    private Cookie token;

    private long expiresIn;

    public Cookie getToken() {
        return token;
    }

    public LoginResponse setToken(String token){
        Cookie cookie = new Cookie("jwtToken", token); 
        cookie.setHttpOnly(true); 
        cookie.setSecure(true); 
        cookie.setMaxAge(3600);
        this.token = cookie; 
        return this;
    }

    public LoginResponse setExpiresIn(long expiresIn){
        this.expiresIn = expiresIn;
        return this;
    }
}

