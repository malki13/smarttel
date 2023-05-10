package com.telcom.ups.monolitico.integracion.controller;

import com.telcom.ups.data.dto.AddTopicoDTO;
import com.telcom.ups.data.dto.AddUrlDTO;
import com.telcom.ups.data.dto.IntegracionDTO;
import com.telcom.ups.data.read.IntegracionRead;
import com.telcom.ups.monolitico.integracion.service.IntegracionService;
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

@Api(value = "REST API relacionado a la entidad de Integracion", tags = "Integraciones")
@RestController
@RequestMapping("/gestion/integracion")
public class IntegracionController {

    @Autowired
    private IntegracionService integracionService;

    @GetMapping("/")
    @ApiOperation(value = "Obtiene toda la lista de Integraciones", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<IntegracionRead>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(integracionService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{integracionId}")
    @ApiOperation(value = "Obtiene una Integracion por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Integracion no encontrada"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> getOne(
            @ApiParam(value = "ID de la integracion", required = true) @PathVariable("integracionId") Integer integracionId
    ) {
        return new ResponseEntity<>(new Response("200", "Integracion encontrada", integracionService.getOne(integracionId)), HttpStatus.OK);
    }

    @PostMapping("/")
    @ApiOperation(value = "Guarda una Integracion según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 202, message = "Integracion creada exitosamente"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creación de la Integracion"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> save(
            @Valid @ApiParam(value = "Información de la integracion", required = true) @RequestBody IntegracionDTO integracionDTO
    ) {
        return new ResponseEntity<>(new Response("200", "Integracion creada con exito", integracionService.save(integracionDTO)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{integracionId}")
    @ApiOperation(value = "Elimina una Integracion por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Integracion no encontrada para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> delete(
            @ApiParam(value = "ID de la integracion", required = true) @PathVariable("integracionId") Integer integracionId
    ) {
        return new ResponseEntity<>(new Response("200", "Integracion eliminada", integracionService.delete(integracionId)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualiza una Integracion por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Integracion no encontrada para actualizar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> update(@RequestBody IntegracionDTO integracionDTO, @PathVariable("id") Integer id) {
        return new ResponseEntity<>(new Response("200", "Integracion actualizada con exito", integracionService.update(integracionDTO, id)), HttpStatus.CREATED);
    }

    @PostMapping("/topico/{integracionId}")
    @ApiOperation(value = "Agregar un topico a la integracion por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Integracion no encontrado para agregar topico"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> addTopico(
            @ApiParam(value = "ID de la integracion", required = true) @PathVariable("integracionId") Integer integracionId,
            @Valid @ApiParam(value = "Información del topico", required = true) @RequestBody AddTopicoDTO addTopicoDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Topico agregado", integracionService.addTopico(integracionId, addTopicoDTO)), HttpStatus.OK);
    }

    @DeleteMapping("/topico/{topicoId}")
    @ApiOperation(value = "Elimina una Topico de la integracion por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Topico no encontrado para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> removeTopico(
            @ApiParam(value = "ID del topico", required = true) @PathVariable("topicoId") Integer topicoId
    ) {
        return new ResponseEntity<>(new Response("200", "Topico eliminado", integracionService.removeTopico(topicoId)), HttpStatus.OK);
    }

    @PostMapping("/url/{integracionId}")
    @ApiOperation(value = "Agrega una url a la integracion por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Integracion no encontrado para agregar url"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> addUrl(
            @ApiParam(value = "ID de la integracion", required = true) @PathVariable("integracionId") Integer integracionId,
            @Valid @ApiParam(value = "Información del url", required = true) @RequestBody AddUrlDTO addUrlDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Topico agregado", integracionService.addUrl(integracionId, addUrlDTO)), HttpStatus.OK);
    }

    @DeleteMapping("/url/{urlId}")
    @ApiOperation(value = "Elimina una url de la integracion por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Url no encontrada para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> removeUrl(
            @ApiParam(value = "ID del url", required = true) @PathVariable("urlId") Integer urlId
    ) {
        return new ResponseEntity<>(new Response("200", "Url eliminada", integracionService.removeUrl(urlId)), HttpStatus.OK);
    }

}
