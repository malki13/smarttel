package com.telcom.ups.monolitico.unidad.controller;

import com.telcom.ups.data.dto.UnidadDTO;
import com.telcom.ups.data.read.UnidadRead;
import com.telcom.ups.monolitico.unidad.service.UnidadService;
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

@Api(description = "REST API relacionado a la entidad de Unidad", tags = "Unidades")
@RestController
@RequestMapping("/facturacion/unidad")
public class UnidadController {

    @Autowired
    private UnidadService unidadService;

    @GetMapping("/")
    @ApiOperation(value = "Obtiene toda la lista de Unidades", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<UnidadRead>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(unidadService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{unidadId}")
    @ApiOperation(value = "Obtiene una Unidad por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Unidad no encontrada"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> getOne(
            @ApiParam(value = "ID de la unidad", required = true) @PathVariable("unidadId") Integer unidadId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Unidad encontrada", unidadService.getOne(unidadId).get()), HttpStatus.OK);
    }

    @PostMapping("/")
    @ApiOperation(value = "Guarda una Unidad según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 202, message = "Unidad creada exitosamente"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creación de la Unidad"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> save(
            @Valid @ApiParam(value = "Información de la unidad", required = true) @RequestBody UnidadDTO unidadDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "Unidad creada", unidadService.save(unidadDTO)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{unidadId}")
    @ApiOperation(value = "Elimina una Unidad por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Unidad no encontrada para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> delete(
            @ApiParam(value = "ID de la unidad", required = true) @PathVariable("unidadId") Integer unidadId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Unidad eliminada", unidadService.delete(unidadId)), HttpStatus.OK);
    }

    @PutMapping("/{unidadId}")
    @ApiOperation(value = "Actualiza una Unidad por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Unidad no encontrada para actualizar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> update(
            @Valid @ApiParam(value = "Información de la unidad", required = true) @RequestBody UnidadDTO unidadDTO,
            @ApiParam(value = "ID de la unidad", required = true) @PathVariable("unidadId") Integer unidadId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Unidad actualizada", unidadService.update(unidadDTO, unidadId)), HttpStatus.OK);
    }
}
