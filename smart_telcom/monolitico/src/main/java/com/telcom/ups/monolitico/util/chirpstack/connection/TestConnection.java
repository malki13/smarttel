package com.telcom.ups.monolitico.util.chirpstack.connection;

import com.google.gson.Gson;
import com.telcom.ups.monolitico.util.chirpstack.AuthorizationHeader;
import com.telcom.ups.monolitico.util.chirpstack.Url;
import com.telcom.ups.monolitico.util.chirpstack.modelo.Login;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TestConnection {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Gson gson;

    public boolean testConnection() {
        try {
            //System.out.println("ENTRE A TEST CONNECTION CHIRPSTACK");
            Login login = new Login();
            login.setEmail("admin");
            login.setPassword("admin");
            ResponseEntity<String> res = restTemplate.postForEntity(Url.urlLogin, gson.toJson(login), String.class);
            //ResponseEntity<String> res = restTemplate.exchange(UrlTarpuq.urlLogin, HttpMethod.POST, AuthorizationHeader.getRequestEntityOnly(login), String.class);
            if (res.getStatusCode() == HttpStatus.OK) {
                //System.out.println("RETORNE TRUE DE TEST CONNECTION CHIRPSTACK");
                return true;
            }
        } catch (Exception exception) {
            System.out.println("ERROR DE TEST CONNECTION CHIRPSTACK " + exception.getMessage());
        }
        return false;
    }

    public String getToken() {
        Login login = new Login();
        login.setEmail("admin");
        login.setPassword("admin");
        ResponseEntity<String> response = restTemplate.exchange(Url.urlLogin, HttpMethod.POST, AuthorizationHeader.getRequestEntityChirpstackLogin(login), String.class);
        JSONObject res = new JSONObject(response.getBody());
        String jwt = res.getString("jwt");
        return jwt;
    }
}
