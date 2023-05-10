package com.telcom.ups.monolitico.dispositivotipo.controller;

import com.telcom.ups.data.dto.DispositivoTipoDTO;
import com.telcom.ups.data.info.DispositivoTipoInfoOnly;
import com.telcom.ups.monolitico.dispositivotipo.service.DispositivoTipoService;
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

@Api(description = "REST API relacionado a la entidad de DispositivoTipo", tags = "Tipos de Dispositivo")
@RestController
@RequestMapping("/gestion/deviceTipo")
public class DispositivoTipoController {

    @Autowired
    private DispositivoTipoService dispositivoTipoService;

    @GetMapping("/")
    @ApiOperation(value = "Obtiene toda la lista de Tipos de Dispositivos", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<DispositivoTipoInfoOnly>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(dispositivoTipoService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{dispositivoTipoId}")
    @ApiOperation(value = "Obtiene un DispositivoTipo por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "DispositivoTipo no encontrado"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> getOne(
            @ApiParam(value = "ID del tipo", required = true) @PathVariable("dispositivoTipoId") Integer dispositivoTipoId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "DispositivoTipo encontrado", dispositivoTipoService.getOne(dispositivoTipoId)), HttpStatus.OK);
    }

    @PostMapping("/")
    @ApiOperation(value = "Guarda un DispositivoTipo según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 202, message = "DispositivoTipo creado exitosamente"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creacion del DispositivoTipo"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> save(
            @Valid @ApiParam(value = "Información del tipo", required = true) @RequestBody DispositivoTipoDTO dispositivoTipoDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "DispositivoTipo creado", dispositivoTipoService.save(dispositivoTipoDTO)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{dispositivoTipoId}")
    @ApiOperation(value = "Elimina un DispositivoTipo por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "DispositivoTipo no encontrado para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> delete(
            @ApiParam(value = "ID del tipo", required = true) @PathVariable("dispositivoTipoId") Integer dispositivoTipoId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "DispositivoTipo eliminado", dispositivoTipoService.delete(dispositivoTipoId)), HttpStatus.OK);
    }

    @PutMapping("/{dispositivoTipoId}")
    @ApiOperation(value = "Actualiza un DispositivoTipo por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "DispositivoTipo no encontrado para actualizar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> update(
            @Valid @ApiParam(value = "Información del tipo", required = true) @RequestBody DispositivoTipoDTO dispositivoTipoDTO,
            @ApiParam(value = "ID del tipo", required = true) @PathVariable("dispositivoTipoId") Integer dispositivoTipoId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "DispositivoTipo actualizado", dispositivoTipoService.update(dispositivoTipoDTO, dispositivoTipoId)), HttpStatus.OK);
    }
}
