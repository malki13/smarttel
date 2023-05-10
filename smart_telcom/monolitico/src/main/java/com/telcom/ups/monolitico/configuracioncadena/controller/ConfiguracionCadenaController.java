package com.telcom.ups.monolitico.configuracioncadena.controller;

import com.telcom.ups.data.dto.ConfiguracionCadenaDTO;
import com.telcom.ups.data.info.ConfiguracionCadenaInfo;
import com.telcom.ups.monolitico.configuracioncadena.service.ConfiguracionCadenaService;
import com.telcom.ups.monolitico.util.response.Response;
import io.swagger.annotations.*;
import org.apache.commons.codec.DecoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(description = "REST API relacionado a la entidad de CadenaConfiguracion", tags = "Cadenas de Configuración")
@RestController
@RequestMapping("/gestion/cadenaConfiguracion")
public class ConfiguracionCadenaController {

    @Autowired
    private ConfiguracionCadenaService configuracionCadenaService;

    @GetMapping("/")
    @ApiOperation(value = "Obtiene toda la lista de Cadenas de Configuracion", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
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
    public ResponseEntity<Page<ConfiguracionCadenaInfo>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(configuracionCadenaService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{configuracionCadenaId}")
    @ApiOperation(value = "Obtiene una Cadena de Configuracion por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Cadena de Configuracion no encontrada"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> getOne(
            @ApiParam(value = "ID de la cadena de configuración", required = true) @PathVariable("configuracionCadenaId") Integer configuracionCadenaId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Cadena de Configuracion encontrada", configuracionCadenaService.getOne(configuracionCadenaId).get()), HttpStatus.OK);
    }

    @PostMapping("/{dispositivoTipoId}")
    @ApiOperation(value = "Guarda una Cadena de Configuracion según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 202, message = "Cadena de Configuracion creada exitosamente"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creacion de la Cadena de Configuracion"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> save(
            @ApiParam(value = "ID del tipo de dispositivo", required = true) @PathVariable("dispositivoTipoId") Integer dispositivoTipoId,
            @Valid @ApiParam(value = "Información de la cadena de configuración", required = true) @RequestBody ConfiguracionCadenaDTO configuracionCadenaDTO
    ) throws DecoderException {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "Cadena de Configuracion creada", configuracionCadenaService.save(dispositivoTipoId, configuracionCadenaDTO)), HttpStatus.CREATED);
    }

    @PostMapping("/base64Only/{dispositivoTipoId}")
    @ApiOperation(value = "Guarda una Cadena de Configuracion según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 202, message = "Cadena de Configuracion creada exitosamente"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creacion de la Cadena de Configuracion"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> saveBase64OnlyFromHexa(
            @ApiParam(value = "ID del tipo de dispositivo", required = true) @PathVariable("dispositivoTipoId") Integer dispositivoTipoId,
            @Valid @ApiParam(value = "Información de la cadena de configuración", required = true) @RequestBody ConfiguracionCadenaDTO configuracionCadenaDTO
    ) throws DecoderException {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "Cadena de Configuracion creada", configuracionCadenaService.saveBase64OnlyFromHexa(dispositivoTipoId, configuracionCadenaDTO)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{configuracionCadenaId}")
    @ApiOperation(value = "Elimina una Cadena de Configuracion por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Cadena de Configuracion no encontrada para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> delete(
            @ApiParam(value = "ID de la cadena de configuración", required = true) @PathVariable("configuracionCadenaId") Integer configuracionCadenaId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Cadena de Configuracion eliminada", configuracionCadenaService.delete(configuracionCadenaId)), HttpStatus.OK);
    }

    @PutMapping("/{dispositivoTipoId}/{configuracionCadenaId}")
    @ApiOperation(value = "Actualiza una Cadena de Configuracion por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Cadena de Configuracion no encontrada para actualizar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> update(
            @ApiParam(value = "ID del tipo de dispositivo", required = true) @PathVariable("dispositivoTipoId") Integer dispositivoTipoId,
            @Valid @ApiParam(value = "Información de la cadena de configuración", required = true) @RequestBody ConfiguracionCadenaDTO configuracionCadenaDTO,
            @ApiParam(value = "ID de la cadena de configuración", required = true) @PathVariable("configuracionCadenaId") Integer configuracionCadenaId
    ) throws DecoderException {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Cadena de Configuracion actualizada", configuracionCadenaService.update(dispositivoTipoId, configuracionCadenaDTO, configuracionCadenaId)), HttpStatus.OK);
    }

    @PutMapping("/updateManual/{dispositivoTipoId}/{configuracionCadenaId}")
    @ApiOperation(value = "Actualiza una Cadena de Configuracion por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Cadena de Configuracion no encontrada para actualizar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> updateManual(
            @ApiParam(value = "ID del tipo de dispositivo", required = true) @PathVariable("dispositivoTipoId") Integer dispositivoTipoId,
            @Valid @ApiParam(value = "Información de la cadena de configuración", required = true) @RequestBody ConfiguracionCadenaDTO configuracionCadenaDTO,
            @ApiParam(value = "ID de la cadena de configuración", required = true) @PathVariable("configuracionCadenaId") Integer configuracionCadenaId
    ) throws DecoderException {
        return new ResponseEntity<>(
                new Response(HttpStatus.OK.toString(),
                        "Cadena de Configuracion actualizada",
                        configuracionCadenaService.updateManual(dispositivoTipoId, configuracionCadenaDTO, configuracionCadenaId)), HttpStatus.OK);
    }

    @GetMapping("/getAllByTipo")
    @ApiOperation(value = "Obtiene toda la lista de Cadenas de configuracion por Tipo de Dispositivo", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<ConfiguracionCadenaInfo>> getAllByDispositivoTipo(@ApiParam(value = "ID del tipo", required = true) @RequestParam Integer idTipo, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(configuracionCadenaService.getAllByDispositivoTipo(idTipo, pageable), HttpStatus.OK);
    }
}
