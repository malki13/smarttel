package com.telcom.ups.monolitico.variante.controller;

import com.telcom.ups.data.dto.VarianteDTO;
import com.telcom.ups.data.read.VarianteRead;
import com.telcom.ups.monolitico.util.response.Response;
import com.telcom.ups.monolitico.variante.service.VarianteService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "REST API relacionado a la entidad de Variante", tags = "Variantes")
@RestController
@RequestMapping("/gestion/variante")
public class VarianteController {

    @Autowired
    private VarianteService varianteService;

    @GetMapping("/")
    @ApiOperation(value = "Obtiene toda la lista de Variantes", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<VarianteRead>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(varianteService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{varianteId}")
    @ApiOperation(value = "Obtiene una Variante por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Variante no encontrada"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> getOne(
            @ApiParam(value = "ID da la variante", required = true) @PathVariable("varianteId") Integer varianteId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Variante encontrada", varianteService.getOne(varianteId).get()), HttpStatus.OK);
    }

    @PostMapping("/{parametroId}")
    @ApiOperation(value = "Guarda una Variante según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 202, message = "Variante creada exitosamente"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creacion de la Variante"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> save(
            @ApiParam(value = "ID da la parametro", required = true) @PathVariable("parametroId") Integer parametroId,
            @Valid @ApiParam(value = "Información da la variante", required = true) @RequestBody VarianteDTO varianteDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "Variante creada", varianteService.save(parametroId, varianteDTO)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{varianteId}")
    @ApiOperation(value = "Elimina una Variante por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Variante no encontrada para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> delete(
            @ApiParam(value = "ID da la variante", required = true) @PathVariable("varianteId") Integer varianteId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Variante eliminada", varianteService.delete(varianteId)), HttpStatus.OK);
    }

    @PutMapping("/{parametroId}/{varianteId}")
    @ApiOperation(value = "Actualiza una Variante por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Variante no encontrada para actualizar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> update(
            @ApiParam(value = "ID da la parametro", required = true) @PathVariable("parametroId") Integer parametroId,
            @Valid @ApiParam(value = "Información da la variante", required = true) @RequestBody VarianteDTO varianteDTO,
            @ApiParam(value = "ID da la variante", required = true) @PathVariable("varianteId") Integer varianteId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Variante actualizada", varianteService.update(parametroId, varianteDTO, varianteId)), HttpStatus.OK);
    }
}
