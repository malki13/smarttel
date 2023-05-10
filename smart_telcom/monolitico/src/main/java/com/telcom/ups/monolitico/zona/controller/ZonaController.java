package com.telcom.ups.monolitico.zona.controller;

import com.telcom.ups.data.dto.ZonaDTO;
import com.telcom.ups.data.info.ZonaInfo;
import com.telcom.ups.monolitico.util.response.Response;
import com.telcom.ups.monolitico.zona.service.ZonaService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "REST API relacionado a la entidad de Zona", tags = "Zonas")
@RestController
@RequestMapping("/zona")
public class ZonaController {

    @Autowired
    private ZonaService zonaService;

    @GetMapping("/")
    @ApiOperation(value = "Obtiene toda la lista de Zonas", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<ZonaInfo>> getAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(zonaService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{zonaId}")
    @ApiOperation(value = "Obtiene una Zona por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Zona no encontrado"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> getOne(
            @ApiParam(value = "ID de la zona", required = true) @PathVariable("zonaId") Integer zonaId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Zona encontrada", zonaService.getOne(zonaId).get()), HttpStatus.OK);
    }

    @PostMapping("/{empresaId}/{adminId}/{tecnicoId}")
    @ApiOperation(value = "Guarda una Zona según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 202, message = "Zona creado exitosamente"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creación de la Zona"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> save(
            @ApiParam(value = "ID de la empresa", required = true) @PathVariable("empresaId") Integer empresaId,
            @ApiParam(value = "ID del administrador", required = true) @PathVariable("adminId") Integer adminId,
            @ApiParam(value = "ID del tecnico", required = true) @PathVariable("tecnicoId") Integer tecnicoId,
            @Valid @ApiParam(value = "Información de la zona", required = true) @RequestBody ZonaDTO zonaDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "Zona creada", zonaService.save(empresaId, adminId, tecnicoId, zonaDTO)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{zonaId}")
    @ApiOperation(value = "Elimina una Zona por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Zona no encontrado para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> delete(
            @ApiParam(value = "ID de la zona", required = true) @PathVariable("zonaId") Integer zonaId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Zona eliminada", zonaService.delete(zonaId)), HttpStatus.OK);
    }

    @PutMapping("/{empresaId}/{adminId}/{tecnicoId}/{zonaId}")
    @ApiOperation(value = "Actualiza una Zona por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Zona no encontrada para actualizar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> update(
            @ApiParam(value = "ID de la empresa", required = true) @PathVariable("empresaId") Integer empresaId,
            @ApiParam(value = "ID del administrador", required = true) @PathVariable("adminId") Integer adminId,
            @ApiParam(value = "ID del tecnico", required = true) @PathVariable("tecnicoId") Integer tecnicoId,
            @ApiParam(value = "ID de la zona", required = true) @PathVariable("zonaId") Integer zonaId,
            @Valid @ApiParam(value = "Información de la zona", required = true) @RequestBody ZonaDTO zonaDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Zona actualizada", zonaService.update(empresaId, adminId, tecnicoId, zonaId, zonaDTO)), HttpStatus.OK);
    }

    @GetMapping("/getAllByEmpresa")
    @ApiOperation(value = "Obtiene toda la lista de Zonas por empresa", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<ZonaInfo>> getAllByEmpresa(
            @ApiParam(value = "ID de la empresa", required = true) @RequestParam Integer idEmpresa,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        return new ResponseEntity<>(zonaService.getAllByEmpresa(idEmpresa, pageable), HttpStatus.OK);
    }

}
