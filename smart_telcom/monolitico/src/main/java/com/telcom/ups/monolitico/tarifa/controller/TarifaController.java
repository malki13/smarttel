package com.telcom.ups.monolitico.tarifa.controller;

import com.telcom.ups.data.dto.TarifaDTO;
import com.telcom.ups.data.info.TarifaInfo;
import com.telcom.ups.monolitico.tarifa.service.TarifaService;
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

@Api(description = "REST API relacionado a la entidad de Tarifa", tags = "Tarifas")
@RestController
@RequestMapping("/gestion/tarifa")
public class TarifaController {

    @Autowired
    private TarifaService tarifaService;

    @GetMapping("/")
    @ApiOperation(value = "Obtiene toda la lista de Tarifas", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<TarifaInfo>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(tarifaService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{tarifaId}")
    @ApiOperation(value = "Obtiene una Tarifa por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Tarifa no encontrada"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> getOne(
            @ApiParam(value = "ID de la tarifa", required = true) @PathVariable("tarifaId") Integer tarifaId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Tarifa encontrada", tarifaService.getOne(tarifaId).get()), HttpStatus.OK);
    }

    @PostMapping("/{empresaId}")
    @ApiOperation(value = "Guarda una Tarifa según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 202, message = "Tarifa creada exitosamente"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creación de la Tarifa"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> save(
            @ApiParam(value = "ID de la empresa", required = true) @PathVariable("empresaId") Integer empresaId,
            @Valid @ApiParam(value = "Información de la tarifa", required = true) @RequestBody TarifaDTO tarifaDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "Tarifa creada", tarifaService.save(empresaId, tarifaDTO)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{tarifaId}")
    @ApiOperation(value = "Elimina una Tarifa por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Tarifa no encontrada para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> delete(
            @ApiParam(value = "ID de la tarifa", required = true) @PathVariable("tarifaId") Integer tarifaId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Tarifa eliminada", tarifaService.delete(tarifaId)), HttpStatus.OK);
    }

    @PutMapping("/{empresaId}/{tarifaId}")
    @ApiOperation(value = "Actualiza una Tarifa por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Tarifa no encontrada para actualizar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> update(
            @ApiParam(value = "ID de la empresa", required = true) @PathVariable("empresaId") Integer empresaId,
            @Valid @ApiParam(value = "Información de la tarifa", required = true) @RequestBody TarifaDTO tarifaDTO,
            @ApiParam(value = "ID de la tarifa", required = true) @PathVariable("tarifaId") Integer tarifaId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Tarifa actualizada", tarifaService.update(empresaId, tarifaDTO, tarifaId)), HttpStatus.OK);
    }

    @GetMapping("/getAllByEmpresa")
    @ApiOperation(value = "Obtiene toda la lista de Tarifas por empresa", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<TarifaInfo>> getAllByEmpresa(
            @ApiParam(value = "ID de la empresa", required = true) @RequestParam Integer idEmpresa,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        return new ResponseEntity<>(tarifaService.getAllByEmpresa(idEmpresa, pageable), HttpStatus.OK);
    }
}
