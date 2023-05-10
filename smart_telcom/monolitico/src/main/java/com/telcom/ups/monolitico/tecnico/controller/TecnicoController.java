package com.telcom.ups.monolitico.tecnico.controller;

import com.telcom.ups.data.info.TecnicoInfo;
import com.telcom.ups.monolitico.tecnico.service.TecnicoService;
import com.telcom.ups.monolitico.util.response.Response;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(description = "REST API relacionado a la entidad de Tecnico", tags = "Tecnicos")
@RestController
@RequestMapping("/tecnico")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping("/")
    @ApiOperation(value = "Obtiene toda la lista de Tecnicos", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
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
    public ResponseEntity<Page<TecnicoInfo>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(tecnicoService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{tenicoId}")
    @ApiOperation(value = "Obtiene un Tecnico por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Tecnico no encontrado"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> getOne(
            @ApiParam(value = "ID del técnico", required = true) @PathVariable("tenicoId") Integer tenicoId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Tecnico encontrado", tecnicoService.getOne(tenicoId).get()), HttpStatus.OK);
    }

    @PostMapping("/{estatusId}/{interventorId}/{empresaId}")
    @ApiOperation(value = "Guarda un Tecnico según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 202, message = "Tecnico creado exitosamente"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creacion del Tecnico"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> save(
            @ApiParam(value = "ID del estatus", required = true) @PathVariable("estatusId") Integer estatusId,
            @ApiParam(value = "ID del interventor", required = true) @PathVariable("interventorId") Integer interventorId,
            @ApiParam(value = "ID de la empresa", required = true) @PathVariable("empresaId") Integer empresaId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "Tecnico creado", tecnicoService.save(estatusId, interventorId, empresaId)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{tenicoId}")
    @ApiOperation(value = "Elimina un Tecnico por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Tecnico no encontrado para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> delete(
            @ApiParam(value = "ID del técnico", required = true) @PathVariable("tenicoId") Integer tenicoId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Tecnico eliminado", tecnicoService.delete(tenicoId)), HttpStatus.OK);
    }

    @PutMapping("/{estatusId}/{interventorId}/{empresaId}/{tenicoId}")
    @ApiOperation(value = "Actualiza un Tecnico por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Tecnico no encontrado para actualizar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> update(
            @ApiParam(value = "ID del estatus", required = true) @PathVariable("estatusId") Integer estatusId,
            @ApiParam(value = "ID del interventor", required = true) @PathVariable("interventorId") Integer interventorId,
            @ApiParam(value = "ID de la empresa", required = true) @PathVariable("empresaId") Integer empresaId,
            @ApiParam(value = "ID del técnico", required = true) @PathVariable("tenicoId") Integer tenicoId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Tecnico actualizado", tecnicoService.update(estatusId, interventorId, empresaId, tenicoId)), HttpStatus.OK);
    }

    @GetMapping("/getAllByEmpresa")
    @ApiOperation(value = "Obtiene toda la lista de Tecnicos por Empresa", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<TecnicoInfo>> getAllByEmpresa(
            @ApiParam(value = "ID de la empresa", required = true) @RequestParam Integer idEmpresa,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        return new ResponseEntity<>(tecnicoService.getAllByEmpresa(idEmpresa, pageable), HttpStatus.OK);
    }
}
