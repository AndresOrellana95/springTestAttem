package com.demo.donations.model.objects;

public class AuthResponse {

    private String jwt;

    public AuthResponse() {}


    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return this.jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public AuthResponse jwt(String jwt) {
        this.jwt = jwt;
        return this;
    }
}
