package com.telcom.ups.monolitico.credential.service;

import com.telcom.ups.data.entities.Credential;
import com.telcom.ups.monolitico.credential.repository.CredentialCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CredentialServiceImpl implements CredentialService {

    @Autowired
    private CredentialCrudRepository credentialCrudRepository;


    /**
     * Devuelve un Credential que provee el repositorio
     * Guarda un credential en la base de datos
     *
     * @param credential
     * @return Credential
     */
    @Override
    public Credential save(Credential credential) {
        return credentialCrudRepository.save(credential);
    }

    /**
     * Devuelve un Credential que provee el repositorio
     * Actualiza un credential en la base de datos
     *
     * @param credential
     * @param codigo
     * @return Credential
     */
    @Override
    public Credential update(Credential credential, String codigo) {
        Optional<Credential> credentialDB = credentialCrudRepository.findByCodigo(codigo);
        if (credentialDB.isPresent()) {
            Credential cred = credentialDB.get();
            cred.setToken(credential.getToken());
            return credentialCrudRepository.save(cred);
        }
        return null;
    }
}
