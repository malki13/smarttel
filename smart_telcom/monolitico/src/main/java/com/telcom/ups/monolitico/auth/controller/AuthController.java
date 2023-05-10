package com.telcom.ups.monolitico.auth.controller;

import com.telcom.ups.data.read.RolRead;
import com.telcom.ups.data.read.UsuarioRead;
import com.telcom.ups.monolitico.auth.dto.AuthenticationRequest;
import com.telcom.ups.monolitico.auth.dto.AuthenticationResponse;
import com.telcom.ups.monolitico.auth.service.AuthService;
import com.telcom.ups.monolitico.usuario.service.UsuarioServiceImpl;
import com.telcom.ups.monolitico.util.security.JwtUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(description = "REST API relacionado a la Autenticación de Usuarios", tags = "Autenticación")
@RestController
@RequestMapping("/security/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    @ApiOperation(value = "Obtiene el jwt dado el correo y contraseña del usuario")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Se obtuvo el token correctamente"),
            @ApiResponse(code = 401, message = "Error en la autenticación")
    })
    public ResponseEntity<AuthenticationResponse> createToken(@Valid @ApiParam(value = "Informacion de las credenciales del Usuario", required = true) @RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
            UserDetails userDetails = authService.loadUserByUsername(authenticationRequest.getEmail());
            if (userDetails != null) {
                String jwt = jwtUtil.generateToken(userDetails);
                UsuarioRead usuarioRead = usuarioService.getByNombre(userDetails.getUsername()).get();
                usuarioRead.setRoles(removeDesactivados(usuarioRead.getRoles()));
                usuarioRead.setPassword(null);
                return new ResponseEntity<>(new AuthenticationResponse(usuarioRead, jwt), HttpStatus.OK);
            }
            return new ResponseEntity<>(new AuthenticationResponse("Bad credentials", null), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new AuthenticationResponse("Bad credentials", null), HttpStatus.UNAUTHORIZED);
        }

    }

    /**
     * Devuelve una lista de RolReads que se filtran en base al estado BLOQUEADO es decir se eliminan de la lista todos los roles
     * que tenga el estado con nombre BLOQUEADO
     *
     * @param rols
     * @return lista de RolReads
     */
    public List<RolRead> removeDesactivados(List<RolRead> rols) {
        for (int i = 0; i < rols.size(); i++) {
            if (rols.get(i).getEstatus().getNombre().equals("BLOQUEADO")) {
                rols.remove(i);
            }
        }
        return rols;
    }

//    @PostMapping("/renew")
//    @ApiOperation(value = "Renueva el token de authenticacion")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "Se obtuvo el token correctamente"),
//            @ApiResponse(code = 401, message = "Error en la token")
//    })
//    public ResponseEntity<AuthenticationRenewResponse> renewToken(@Valid @ApiParam(value = "Información del token a ser actualizado", required = true) @RequestBody AuthenticationRenewRequest request) throws Exception{
//        try {
//            //System.out.println("TOKEN QUE LLEGA "+ request.getToken());
//            String nombre = jwtUtil.getUserNameFromToken(request.getToken());
//            //System.out.println("EMAIL EXTRAIDO "+ nombre);
//            UsuarioDTO usuarioDTO = usuarioService.getByNombre(nombre).isPresent() ? usuarioService.getByNombre(nombre).get() : null;
//            if(usuarioDTO != null){
//                UserDetails userDetails = authService.loadUserByUsername(nombre);
//                String jwt = jwtUtil.generateToken(userDetails);
//                return new ResponseEntity<>(new AuthenticationRenewResponse(jwt), HttpStatus.OK);
//            }
//            return new ResponseEntity<>(new AuthenticationRenewResponse("Token invalido"), HttpStatus.UNAUTHORIZED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new AuthenticationRenewResponse("Token invalido"), HttpStatus.UNAUTHORIZED);
//        }
//
//    }

    @PostMapping("/loginDispositivo")
    @ApiOperation(value = "Obtiene el jwt dado el correo y contraseña del usuario")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Se obtuvo el token correctamente"),
            @ApiResponse(code = 401, message = "Error en la autenticación")
    })
    public ResponseEntity<AuthenticationResponse> createTokenDevice(@Valid @ApiParam(value = "Informacion de las credenciales del Usuario", required = true) @RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
            UserDetails userDetails = authService.loadUserByUsername(authenticationRequest.getEmail());
            if (userDetails != null) {
                String jwt = jwtUtil.generateToken(userDetails);
                return new ResponseEntity<>(new AuthenticationResponse(true, jwt), HttpStatus.OK);
            }
            return new ResponseEntity<>(new AuthenticationResponse(false, null), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new AuthenticationResponse(false, null), HttpStatus.UNAUTHORIZED);
        }

    }

}
