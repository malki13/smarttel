package com.telcom.ups.monolitico.util.chirpstack;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

public class AuthorizationHeader {

    public static HttpHeaders getHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Grpc-Metadata-Authorization", "Bearer " + token);
        return headers;
    }

    public static HttpHeaders getHeadersLoginChirpstack() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public static HttpEntity<String> getRequestEntity(Object data, String token) {
        Gson gson = new Gson();
        return new HttpEntity<String>(gson.toJson(data), getHeaders(token));
    }

    public static HttpEntity<String> getRequestEntityChirpstackLogin(Object data) {
        Gson gson = new Gson();
        return new HttpEntity<String>(gson.toJson(data), getHeadersLoginChirpstack());
    }

    public static HttpEntity<String> getRequestEntityOnly(Object data) {
        Gson gson = new Gson();
        return new HttpEntity<String>(gson.toJson(data));
    }

    public static HttpEntity<Void> getRequestEntityHeaders(String token) {
        return new HttpEntity<Void>(getHeaders(token));
    }
}
