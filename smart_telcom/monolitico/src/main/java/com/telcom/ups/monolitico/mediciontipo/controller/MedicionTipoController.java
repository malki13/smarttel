package com.telcom.ups.monolitico.mediciontipo.controller;

import com.telcom.ups.data.dto.MedicionTipoDTO;
import com.telcom.ups.data.read.MedicionTipoRead;
import com.telcom.ups.monolitico.mediciontipo.service.MedicionTipoService;
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

@Api(description = "REST API relacionado a la entidad de MedicionTipo", tags = "Tipos de medicion")
@RestController
@RequestMapping("/gestion/medicionTipo")
public class MedicionTipoController {

    @Autowired
    private MedicionTipoService medicionTipoService;

    @GetMapping("/")
    @ApiOperation(value = "Obtiene toda la lista de Tipos de Mediciones", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", dataType = "integer", paramType = "query",
                    value = "Indica la distancia (desplazamiento) desde el inicio del objeto hasta un punto o elemento dado"),
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
    public ResponseEntity<Page<MedicionTipoRead>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(medicionTipoService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{medicionTipoId}")
    @ApiOperation(value = "Obtiene un MedicionTipo por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "MedicionTipo no encontrado"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> getOne(
            @ApiParam(value = "ID del tipo", required = true) @PathVariable("medicionTipoId") Integer medicionTipoId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "MedicionTipo encontrado", medicionTipoService.getOne(medicionTipoId)), HttpStatus.OK);
    }

    @PostMapping("/")
    @ApiOperation(value = "Guarda un MedicionTipo según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 202, message = "MedicionTipo creado exitosamente"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creacion del MedicionTipo"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> save(
            @Valid @ApiParam(value = "Información del tipo", required = true) @RequestBody MedicionTipoDTO medicionTipoDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "MedicionTipo creado", medicionTipoService.save(medicionTipoDTO)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{medicionTipoId}")
    @ApiOperation(value = "Elimina un MedicionTipo por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "MedicionTipo no encontrado para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> delete(
            @ApiParam(value = "ID del tipo", required = true) @PathVariable("medicionTipoId") Integer medicionTipoId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "MedicionTipo eliminado", medicionTipoService.delete(medicionTipoId)), HttpStatus.OK);
    }

    @PutMapping("/{medicionTipoId}")
    @ApiOperation(value = "Actualiza un MedicionTipo por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "MedicionTipo no encontrado para actualizar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> update(
            @Valid @ApiParam(value = "Información del tipo", required = true) @RequestBody MedicionTipoDTO medicionTipoDTO,
            @ApiParam(value = "ID del tipo", required = true) @PathVariable("medicionTipoId") Integer medicionTipoId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "MedicionTipo actualizado", medicionTipoService.update(medicionTipoDTO, medicionTipoId)), HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    @ApiOperation(value = "Obtiene un MedicionTipo por su codigo", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "MedicionTipo no encontrado"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> getOne(
            @ApiParam(value = "Codigo de medicion", required = true) @PathVariable("codigo") String codigo
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "MedicionTipo encontrado", medicionTipoService.findByCodigo(codigo)), HttpStatus.OK);
    }
}
