package com.telcom.ups.monolitico.estatus.controller;

import com.telcom.ups.data.dto.EstatusDTO;
import com.telcom.ups.data.read.EstatusRead;
import com.telcom.ups.monolitico.estatus.service.EstatusService;
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

@Api(description = "REST API relacionado a la entidad de Estatus", tags = "Estatus")
@RestController
@RequestMapping("/estatus")
public class EstatusController {

    @Autowired
    private EstatusService estatusService;

    @GetMapping("/")
    @ApiOperation(value = "Obtiene toda la lista de Estatus", authorizations = {@Authorization(value = "JWT")})
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
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Page<EstatusRead>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(estatusService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{estatusId}")
    @ApiOperation(value = "Obtiene un Estatus por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "estatus no encontrado"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> getOne(
            @ApiParam(value = "ID del estatus", required = true) @PathVariable("estatusId") Integer estatusId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Estatus encontrado", estatusService.getOne(estatusId).get()), HttpStatus.OK);
    }

    @PostMapping("/")
    @ApiOperation(value = "Guarda un Estatus según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 202, message = "Estatus creado exitosamente"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creación del estatus"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> save(@Valid @ApiParam(value = "Información del estatus", required = true) @RequestBody EstatusDTO estatusDTO) {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "Estatus creado", estatusService.save(estatusDTO)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{estatusId}")
    @ApiOperation(value = "Elimina un Estatus por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Estatus no encontrado para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> delete(@ApiParam(value = "ID del estatus", required = true) @PathVariable("estatusId") Integer estatusId) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Estatus eliminado", estatusService.delete(estatusId)), HttpStatus.OK);
    }

    @PutMapping("/{estatusId}")
    @ApiOperation(value = "Actualiza un Estatus por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "estatus no encontrada para actualizar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> update(
            @Valid @ApiParam(value = "Información del estatus", required = true) @RequestBody EstatusDTO estatusDTO,
            @ApiParam(value = "ID del estatus", required = true) @PathVariable("id") Integer estatusId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Estatus actualizado", estatusService.update(estatusDTO, estatusId)), HttpStatus.OK);
    }
}
