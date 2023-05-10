package com.telcom.ups.monolitico.parametro.controller;

import com.telcom.ups.data.dto.ParametroDTO;
import com.telcom.ups.data.read.ParametroRead;
import com.telcom.ups.monolitico.parametro.service.ParametroService;
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

@Api(description = "REST API relacionado a la entidad de Parametro", tags = "Parametros")
@RestController
@RequestMapping("/gestion/parametro")
public class ParametroController {

    @Autowired
    private ParametroService parametroService;

    @GetMapping("/")
    @ApiOperation(value = "Obtiene toda la lista de Parametros", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<ParametroRead>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(parametroService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{parametroId}")
    @ApiOperation(value = "Obtiene un Parametro por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Parametro no encontrado"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> getOne(
            @ApiParam(value = "ID del parámetro", required = true) @PathVariable("parametroId") Integer parametroId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Parametro encontrado", parametroService.getOne(parametroId).get()), HttpStatus.OK);
    }

    @PostMapping("/{configuracionId}")
    @ApiOperation(value = "Guarda un Parametro según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 202, message = "Parametro creado exitosamente"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creacion del Parametro"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> save(
            @ApiParam(value = "ID de la configuracion", required = true) @PathVariable("configuracionId") Integer configuracionId,
            @Valid @ApiParam(value = "Información del parámetro", required = true) @RequestBody ParametroDTO parametroDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "Parametro creado", parametroService.save(configuracionId, parametroDTO)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{parametroId}")
    @ApiOperation(value = "Elimina un Parametro por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Parametro no encontrado para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> delete(
            @ApiParam(value = "ID del parámetro", required = true) @PathVariable("parametroId") Integer parametroId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Parametro eliminado", parametroService.delete(parametroId)), HttpStatus.OK);
    }

    @PutMapping("/{configuracionId}/{parametroId}")
    @ApiOperation(value = "Actualiza un Parametro por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Parametro no encontrado para actualizar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> update(
            @ApiParam(value = "ID de la configuracion", required = true) @PathVariable("configuracionId") Integer configuracionId,
            @Valid @ApiParam(value = "Información del parámetro", required = true) @RequestBody ParametroDTO parametroDTO,
            @ApiParam(value = "ID del parámetro", required = true) @PathVariable("parametroId") Integer parametroId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Parametro actualizado", parametroService.update(configuracionId, parametroDTO, parametroId)), HttpStatus.OK);
    }
}
