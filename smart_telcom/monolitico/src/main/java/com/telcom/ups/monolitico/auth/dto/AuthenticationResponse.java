package com.telcom.ups.monolitico.auth.dto;

public class AuthenticationResponse {

    private Object resultado;
    private String jwt;

    public AuthenticationResponse(Object resultado, String jwt) {
        this.resultado = resultado;
        this.jwt = jwt;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "jwt='" + jwt + '\'' +
                '}';
    }

    public Object getResultado() {
        return resultado;
    }

    public void setResultado(Object resultado) {
        this.resultado = resultado;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
