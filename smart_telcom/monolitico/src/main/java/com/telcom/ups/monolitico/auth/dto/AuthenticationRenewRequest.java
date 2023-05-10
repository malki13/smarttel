package com.telcom.ups.monolitico.auth.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AuthenticationRenewRequest {

    @NotNull
    @NotBlank
    @NotEmpty
    private String token;

    public AuthenticationRenewRequest() {
    }

    public AuthenticationRenewRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
