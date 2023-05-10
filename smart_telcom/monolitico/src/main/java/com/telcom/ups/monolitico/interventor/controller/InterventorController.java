package com.telcom.ups.monolitico.interventor.controller;

import com.telcom.ups.data.dto.InterventorDTO;
import com.telcom.ups.data.entities.Usuario;
import com.telcom.ups.data.info.InterventorInfo;
import com.telcom.ups.monolitico.interventor.service.InterventorService;
import com.telcom.ups.monolitico.usuario.repository.UsuarioCrudRepository;
import com.telcom.ups.monolitico.util.response.Response;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Api(description = "REST API relacionado a la entidad de Interventor", tags = "Interventores")
@RestController
@RequestMapping("/interventor")
public class InterventorController {

    @Autowired
    private InterventorService interventorService;

    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/")
    @ApiOperation(value = "Obtiene toda la lista de Interventores", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<InterventorInfo>> findAll(
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        return new ResponseEntity<>(interventorService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{interventorId}")
    @ApiOperation(value = "Obtiene un Interventor por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Interventor no encontrado"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> getOne(
            @ApiParam(value = "ID del interventor", required = true) @PathVariable("interventorId") Integer interventorId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Interventor encontrado", interventorService.getOne(interventorId).get()), HttpStatus.OK);
    }

    @PostMapping(value = "/{interventorTipoId}")
    @ApiOperation(value = "Guarda un Interventor según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 202, message = "Interventor creado exitosamente"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creación del Interventor"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> save(
            @ApiParam(value = "ID del tipo de interventor", required = true) @PathVariable("interventorTipoId") Integer interventorTipoId,
            @Valid @ApiParam(value = "Información del interventor", required = true) @RequestBody InterventorDTO interventorDTO
    ) throws Exception {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "Interventor creado", interventorService.save(interventorTipoId, interventorDTO)), HttpStatus.OK);
    }

    @DeleteMapping("/{interventorId}")
    @ApiOperation(value = "Elimina un Interventor por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Interventor no encontrado para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> delete(
            @ApiParam(value = "ID del interventor", required = true) @PathVariable("interventorId") Integer interventorId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Interventor eliminado", interventorService.delete(interventorId)), HttpStatus.OK);
    }

    @PutMapping("/{interventorId}/{interventorTipoId}")
    @ApiOperation(value = "Actualiza un Interventor por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Interventor no encontrada para actualizar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> update(
            @Valid @ApiParam(value = "Información del interventor", required = true) @RequestBody InterventorDTO interventorDTO,
            @ApiParam(value = "ID del interventor", required = true) @PathVariable("interventorId") Integer interventorId,
            @ApiParam(value = "ID del tipo de interventor", required = true) @PathVariable("interventorTipoId") Integer interventorTipoId
    ) throws Exception {
        String mensaje = "Interventor actualizado";
        Optional<InterventorInfo> interventorInfo = interventorService.getOne(interventorId);
        if (interventorInfo.isPresent()) {
            InterventorInfo info = interventorInfo.get();
            Optional<Usuario> usuario = usuarioCrudRepository.findByNombre(info.getEmail());
            if (usuario.isPresent()) {
                mensaje = mensaje + " NOTA: Este interventor es un usuario del sistema, por consiguiente el email del interventor no se actualizara en este proceso.";
            }
        }
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), mensaje, interventorService.update(interventorId, interventorTipoId, interventorDTO)), HttpStatus.OK);
    }

    @GetMapping("/searchByCodigo")
    @ApiOperation(value = "Obtiene una lista de Interventores por su codigo", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> findByCodigo(
            @ApiParam(value = "Código del interventor", required = true) @RequestParam String codigo
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Interventor encontrado", interventorService.findByCodigo(codigo).get()), HttpStatus.OK);
    }

    @GetMapping("/searchByApellido")
    @ApiOperation(value = "Obtiene una lista de Interventores por su apellido", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<List<InterventorInfo>> findByApellido(
            @ApiParam(value = "Apellido del interventor", required = true) @RequestParam String apellido
    ) {
        return new ResponseEntity<>(interventorService.searchByApellido(apellido), HttpStatus.OK);
    }


}
