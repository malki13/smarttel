package com.telcom.ups.monolitico.zonaqueutemp.controller;

import com.telcom.ups.data.dto.ZonaQueueTempDTO;
import com.telcom.ups.data.dto.ZonaQueueTempOneDTO;
import com.telcom.ups.data.info.ZonaQueueTempInfo;
import com.telcom.ups.data.read.ZonaQueueRead;
import com.telcom.ups.monolitico.util.response.Response;
import com.telcom.ups.monolitico.zonaqueutemp.service.ZonaQueueTempService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@Api(description = "REST API relacionado a la entidad de ZonaQueue", tags = "ZonaQueue")
@RestController
@RequestMapping("/comunicacion/zonaQueue")
public class ZonaQueueTempController {

    @Autowired
    private ZonaQueueTempService zonaQueueTempService;

    @GetMapping("/getAllLogs/")
    @ApiOperation(value = "Obtiene toda la lista de Logs de ZonaQueue", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<ZonaQueueRead>> getAllLogs(
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        return new ResponseEntity<>(zonaQueueTempService.findAllLogs(pageable), HttpStatus.OK);
    }

    @GetMapping("/getAllLogsByZona/")
    @ApiOperation(value = "Obtiene toda la lista de Logs de ZonaQueue por Zona", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<ZonaQueueRead>> getAllLogsByZona(
            @ApiParam(value = "Id de la zona", required = true) @RequestParam Integer idZona,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        return new ResponseEntity<>(zonaQueueTempService.findAllLogsByZona(idZona, pageable), HttpStatus.OK);
    }

    @GetMapping("/getAllJob/")
    @ApiOperation(value = "Obtiene toda la lista de ZonaQueue", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<ZonaQueueTempInfo>> getAllJob(
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        return new ResponseEntity<>(zonaQueueTempService.getAllJob(pageable), HttpStatus.OK);
    }

    @GetMapping("/getAllJobByZona/")
    @ApiOperation(value = "Obtiene toda la lista de ZonaQueue por zona", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<ZonaQueueTempInfo>> getAllJobByZona(
            @ApiParam(value = "Id de la zona", required = true) @RequestParam Integer idZona,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        return new ResponseEntity<>(zonaQueueTempService.findAllColaByZona(idZona, pageable), HttpStatus.OK);
    }

    @GetMapping("/estado/{idZona}")
    @ApiOperation(value = "Obtiene el estado de la comunicacion de la zona", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Estado no encontrado"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> getEstadoZona(
            @ApiParam(value = "id de la zona", required = true) @PathVariable("idZona") Integer idZona
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Estado encontrado", zonaQueueTempService.geEstado(idZona)), HttpStatus.OK);
    }

    @GetMapping("/{zonaId}")
    @ApiOperation(value = "Obtiene el dato encolado por id de zona", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "ZonaQueue no encontrado"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> getOneZona(
            @ApiParam(value = "Id de la zona", required = true) @PathVariable("zonaId") Integer zonaId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "ZonaQueue encontrado", zonaQueueTempService.findByZonaId(zonaId)), HttpStatus.OK);
    }

    @PostMapping("/{usuarioId}/{zonaId}")
    @ApiOperation(value = "Guardar un dato en la cola por cada medidor de la zona para proceder a comunicar con el dispositivo", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 202, message = "ZonaQueue creado exitosamente"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creación del ZonaQueue"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> saveZona(
            @ApiParam(value = "Id de la usuario", required = true) @PathVariable("usuarioId") Integer usuarioId,
            @ApiParam(value = "Id de la zona", required = true) @PathVariable("zonaId") Integer zonaId,
            @Valid @ApiParam(value = "Información de la ZonaQueue", required = true) @RequestBody ZonaQueueTempDTO zonaQueueTempDTO
    ) throws ParseException {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "ZonaQueue creado", zonaQueueTempService.save(zonaQueueTempDTO, usuarioId, zonaId)), HttpStatus.CREATED);
    }

    @PostMapping("/backEncolarMedidor/{usuarioId}/{zonaId}")
    @ApiOperation(value = "Vuelve un encolar de un medidor de la zona para proceder a comunicar con el dispositivo", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 202, message = "ZonaQueue encontrado para volver a encolar"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creación del ZonaQueue"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> backEncolarMedidor(
            @ApiParam(value = "Id de la usuario", required = true) @PathVariable("usuarioId") Integer usuarioId,
            @ApiParam(value = "Id de la zona", required = true) @PathVariable("zonaId") Integer zonaId,
            @Valid @ApiParam(value = "Información de la ZonaQueue", required = true) @RequestBody ZonaQueueTempOneDTO zonaQueueTempOneDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "ZonaQueue encolado correctamente", zonaQueueTempService.backEncolarMedidor(zonaQueueTempOneDTO, usuarioId, zonaId)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{zonaId}")
    @ApiOperation(value = "Elimina un ZonaQueue de la cola", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "ZonaQueue no encontrado para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> delete(
            @ApiParam(value = "Id de la zona", required = true) @PathVariable("zonaId") Integer zonaId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "ZonaQueue eliminado", zonaQueueTempService.delete(zonaId)), HttpStatus.OK);
    }

    @DeleteMapping("/deleteByDeveui/{deveui}")
    @ApiOperation(value = "Elimina un ZonaQueue de la cola por deveui", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "ZonaQueue no encontrado para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> deleteByDeveui(
            @ApiParam(value = "Deveui del dispositivo", required = true) @PathVariable("deveui") String deveui
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "DispositivoQueue eliminado", zonaQueueTempService.deleteByDeveui(deveui)), HttpStatus.OK);
    }
}
