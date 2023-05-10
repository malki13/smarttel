package com.telcom.ups.monolitico.auth.dto;

public class AuthenticationRenewResponse {

    private String jwt;

    public AuthenticationRenewResponse() {
    }

    public AuthenticationRenewResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
