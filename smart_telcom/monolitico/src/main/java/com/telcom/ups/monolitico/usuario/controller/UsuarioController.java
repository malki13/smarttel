package com.telcom.ups.monolitico.usuario.controller;

import com.telcom.ups.data.dto.AuthPasswordDTO;
import com.telcom.ups.data.dto.UsuarioDTO;
import com.telcom.ups.data.info.UsuarioInfo;
import com.telcom.ups.data.read.UsuarioRead;
import com.telcom.ups.monolitico.usuario.service.UsuarioServiceImpl;
import com.telcom.ups.monolitico.util.response.Response;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(description = "REST API relacionado a la entidad Usuario", tags = "Usuarios")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping("/")
    @ApiOperation(value = "Obtiene toda la lista de Usuarios", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", dataType = "integer", paramType = "query",
                    value = " Indica la distancia (desplazamiento) desde el inicio del objeto hasta un punto o elemento dado"),
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", readOnly = true,
                    value = "Numero de Página que desea recuperar (0..N)"),
            @ApiImplicitParam(name = "paged", dataType = "boolean", paramType = "query",
                    value = "Indica si se aplica o no se aplica la paginación al resultado de la consulta"),
            @ApiImplicitParam(name = "pageNumber", dataType = "integer", paramType = "query",
                    value = "Numero de Página que desea recuperar (0..N)"),
            @ApiImplicitParam(name = "pageSize", dataType = "integer", paramType = "query",
                    value = "Número de registros por página"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", readOnly = true,
                    value = "Número de registros por página"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Criterios de orden en el formato: propiedad (, asc | desc). " +
                            "El orden de clasificación predeterminado es ascendente. " +
                            "Se admiten varios criterios de clasificación."),
            @ApiImplicitParam(name = "sort.sorted", dataType = "boolean", paramType = "query",
                    value = "Indica si se aplica o no se aplica el orden a la clasificación del resultado de la consulta"),
            @ApiImplicitParam(name = "sort.unsorted", dataType = "boolean", paramType = "query",
                    value = "Indica si se aplica o no se aplica el desorden a la clasificación del resultado de la consulta"),
            @ApiImplicitParam(name = "unpaged", dataType = "boolean", paramType = "query",
                    value = "Indica si se aplica o no se aplica que el resultado de la consulta no se paginado")
    })
    public ResponseEntity<Page<UsuarioInfo>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(usuarioService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{usuarioId}")
    @ApiOperation(value = "Obtiene un Usuario por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Usuario no encontrado"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> getOne(@ApiParam(value = "ID del usuario", required = true) @PathVariable("usuarioId") Integer usuarioId) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Usuario encontrado", usuarioService.getOne(usuarioId).get()), HttpStatus.OK);
    }

    @GetMapping("/getByEmail/")
    @ApiOperation(value = "Obtiene un Usuario por su email", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Usuario no encontrado"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> getByEmail(
            @ApiParam(value = "Email del usuario", required = true) @RequestParam String email
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Usuario encontrado", usuarioService.getByNombre(email).get()), HttpStatus.OK);
    }

    @PostMapping("/{estatusId}/{interventorId}/{empresaId}")
    @ApiOperation(value = "Guarda un Usuario según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 202, message = "Usuario creado exitosamente"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creación del Usuario"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> save(
            @PathVariable("estatusId") Integer estatusId,
            @PathVariable("interventorId") Integer interventorId,
            @PathVariable("empresaId") Integer empresaId,
            @Valid @ApiParam(value = "Información del usuario", required = true) @RequestBody UsuarioDTO usuarioDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "Usuario creado", usuarioService.save(estatusId, interventorId, empresaId, usuarioDTO)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{usuarioId}")
    @ApiOperation(value = "Elimina un Usuario por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Usuario no encontrado para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> delete(@ApiParam(value = "ID del usuario", required = true) @PathVariable("usuarioId") Integer usuarioId) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Usuario eliminado", usuarioService.delete(usuarioId)), HttpStatus.OK);
    }

    @PutMapping("/{estatusId}/{interventorId}/{empresaId}/{usuarioId}")
    @ApiOperation(value = "Actualiza un Usuario por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Usuario no encontrado para actualizar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> update(
            @PathVariable("estatusId") Integer estatusId,
            @PathVariable("interventorId") Integer interventorId,
            @PathVariable("empresaId") Integer empresaId,
            @ApiParam(value = "ID del usuario", required = true) @PathVariable("usuarioId") Integer usuarioId,
            @Valid @ApiParam(value = "Información del usuario", required = true) @RequestBody UsuarioDTO usuarioDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Usuario actualizado", usuarioService.update(estatusId, interventorId, empresaId, usuarioId, usuarioDTO)), HttpStatus.OK);
    }

    @GetMapping("/searchByNombre")
    @ApiOperation(value = "Obtiene una la lista de Usuarios por su nombre", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<List<UsuarioRead>> findByNombre(
            @ApiParam(value = "Nombre del usuario", required = true) @RequestParam String nombre
    ) {
        return new ResponseEntity<>(usuarioService.searchByNombre(nombre), HttpStatus.OK);
    }

    @PostMapping("/updatePassword")
    @ApiOperation(value = "Actualiza la contraseña de un usuario según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 202, message = "Contraseña actualizada exitosamente"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la actualizacion de la contraseña"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> updatePassword(
            @Valid @ApiParam(value = "Información de la contraseña del usuario", required = true) @RequestBody AuthPasswordDTO authPasswordDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "Contraseña actualizada exitosamente", usuarioService.updatePassword(authPasswordDTO.getId(), authPasswordDTO.getPassword())), HttpStatus.CREATED);
    }

    @GetMapping("/getAllByEmpresa")
    @ApiOperation(value = "Obtiene toda la lista de Usuarios por empresa", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", dataType = "integer", paramType = "query",
                    value = " Indica la distancia (desplazamiento) desde el inicio del objeto hasta un punto o elemento dado"),
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", readOnly = true,
                    value = "Numero de Página que desea recuperar (0..N)"),
            @ApiImplicitParam(name = "paged", dataType = "boolean", paramType = "query",
                    value = "Indica si se aplica o no se aplica la paginación al resultado de la consulta"),
            @ApiImplicitParam(name = "pageNumber", dataType = "integer", paramType = "query",
                    value = "Numero de Página que desea recuperar (0..N)"),
            @ApiImplicitParam(name = "pageSize", dataType = "integer", paramType = "query",
                    value = "Número de registros por página"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", readOnly = true,
                    value = "Número de registros por página"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Criterios de orden en el formato: propiedad (, asc | desc). " +
                            "El orden de clasificación predeterminado es ascendente. " +
                            "Se admiten varios criterios de clasificación."),
            @ApiImplicitParam(name = "sort.sorted", dataType = "boolean", paramType = "query",
                    value = "Indica si se aplica o no se aplica el orden a la clasificación del resultado de la consulta"),
            @ApiImplicitParam(name = "sort.unsorted", dataType = "boolean", paramType = "query",
                    value = "Indica si se aplica o no se aplica el desorden a la clasificación del resultado de la consulta"),
            @ApiImplicitParam(name = "unpaged", dataType = "boolean", paramType = "query",
                    value = "Indica si se aplica o no se aplica que el resultado de la consulta no se paginado")
    })
    public ResponseEntity<Page<UsuarioInfo>> getAllByEmpresa(
            @ApiParam(value = "ID de la empresa", required = true) @RequestParam Integer idEmpresa, @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        return new ResponseEntity<>(usuarioService.getAllByEmpresa(idEmpresa, pageable), HttpStatus.OK);
    }

    @PostMapping("/{usuarioId}/{rolId}")
    @ApiOperation(value = "Elimina un Usuario por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Usuario no encontrado para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> addRol(
            @ApiParam(value = "ID del usuario", required = true) @PathVariable("usuarioId") Integer usuarioId,
            @ApiParam(value = "ID del rol", required = true) @PathVariable("rolId") Integer rolId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Rol agregado", usuarioService.addRol(usuarioId, rolId)), HttpStatus.OK);
    }

    @DeleteMapping("/{usuarioId}/{rolId}")
    @ApiOperation(value = "Elimina un Usuario por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Usuario no encontrado para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> removeRol(
            @ApiParam(value = "ID del usuario", required = true) @PathVariable("usuarioId") Integer usuarioId,
            @ApiParam(value = "ID del rol", required = true) @PathVariable("rolId") Integer rolId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Rol eliminado", usuarioService.removeRol(usuarioId, rolId)), HttpStatus.OK);
    }
}
