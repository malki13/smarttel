package com.telcom.ups.monolitico.auth.service;


import com.telcom.ups.data.read.UsuarioRead;
import com.telcom.ups.monolitico.usuario.service.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UsuarioServiceImpl usuarioService;


    /**
     * Devuelve un UserDetails que provee el servicio
     * Busca la información de un usuario en base a un nombre, si esta existe retorna los detalles del mismo para proceder a authenticar el mismo
     *
     * @param nombre
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        UsuarioRead usuarioRead = usuarioService.getByNombre(nombre).isPresent() ? usuarioService.getByNombre(nombre).get() : null;
        if (usuarioRead != null) {
            return new org.springframework.security.core.userdetails.User(usuarioRead.getNombre(), usuarioRead.getPassword(), new ArrayList<>());
        }
        return null;
    }
}
