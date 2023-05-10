package com.telcom.ups.monolitico.util.chirpstack.authentication;


import com.telcom.ups.data.entities.Credential;
import com.telcom.ups.monolitico.credential.repository.CredentialCrudRepository;
import com.telcom.ups.monolitico.credential.service.CredentialServiceImpl;
import com.telcom.ups.monolitico.util.chirpstack.AuthorizationHeader;
import com.telcom.ups.monolitico.util.chirpstack.Url;
import com.telcom.ups.monolitico.util.chirpstack.connection.TestConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class InternalServiceChirpstack {

    @Autowired
    private TestConnection testConnectionChirpstack;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CredentialCrudRepository credentialCrudRepository;

    @Autowired
    private CredentialServiceImpl implCredentialService;

    public Credential getValidCredentialCS(String nombreToken) {
        try {
            Optional<Credential> credentialDB = credentialCrudRepository.findByCodigo(nombreToken);
            if (credentialDB.isPresent()) {
                ResponseEntity<String> testChirp = restTemplate.exchange(Url.urlUsers, HttpMethod.GET, AuthorizationHeader.getRequestEntityHeaders(credentialDB.get().getToken()), String.class);
                if (testChirp.getStatusCode() == HttpStatus.OK) {
                    return credentialDB.get();
                }
                return implCredentialService.update(new Credential(testConnectionChirpstack.getToken()), nombreToken);
            }
            return implCredentialService.save(new Credential(nombreToken, testConnectionChirpstack.getToken()));
        } catch (Exception exception) {
            return implCredentialService.update(new Credential(testConnectionChirpstack.getToken()), nombreToken);
        }
    }

    public String getTokenCS() {
        return getValidCredentialCS("tokenCS").getToken();
    }
}
