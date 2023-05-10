package com.telcom.ups.monolitico.credential.service;


import com.telcom.ups.data.entities.Credential;

public interface CredentialService {

    Credential save(Credential credential);

    Credential update(Credential credential, String codigo);
}
