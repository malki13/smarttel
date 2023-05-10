package com.telcom.ups.monolitico.dispositivoqueuetemp.controller;

import com.telcom.ups.data.dto.DispositivoQueueTempDTO;
import com.telcom.ups.data.info.DispositivoQueueInfo;
import com.telcom.ups.data.info.DispositivoQueueTempInfo;
import com.telcom.ups.monolitico.dispositivoqueuetemp.service.DispositivoQueueTempService;
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
import java.text.ParseException;

@Api(description = "REST API relacionado a la entidad de DispositivoQueue", tags = "DispositivoQueue")
@RestController
@RequestMapping("/comunicacion/dispostivoQueue")
public class DispositivoQueueTempController {

    @Autowired
    private DispositivoQueueTempService dispositivoQueueTempService;

    @GetMapping("/findAllLogs/")
    @ApiOperation(value = "Obtiene toda la lista de de Logs de DispositivoQueue", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<DispositivoQueueInfo>> findAllLogs(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(dispositivoQueueTempService.findAllLogs(pageable), HttpStatus.OK);
    }

    @GetMapping("/getAllJob/")
    @ApiOperation(value = "Obtiene toda la lista de DispositivoQueueTemp", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<DispositivoQueueTempInfo>> findAllJob(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(dispositivoQueueTempService.getAllJob(pageable), HttpStatus.OK);
    }

    @GetMapping("/findAllLogsDeveui/")
    @ApiOperation(value = "Obtiene toda la lista de DispositivoQueueTemp", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<DispositivoQueueInfo>> getAllByDeveui(
            @ApiParam(value = "Deveui del dispositivo", required = true) @RequestParam String deveui,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        return new ResponseEntity<>(dispositivoQueueTempService.getAllByDeveui(deveui, pageable), HttpStatus.OK);
    }

    @GetMapping("/estado/{deveui}")
    @ApiOperation(value = "Obtiene el estado de la comunicacion del medidor", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Estado no encontrado"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> getEstadoDispositivo(
            @ApiParam(value = "deveui del dispositivo", required = true) @PathVariable("deveui") String deveui
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Estado encontrado", dispositivoQueueTempService.geEstado(deveui)), HttpStatus.OK);
    }

    @GetMapping("/{deveui}")
    @ApiOperation(value = "Obtiene el dato encolado por deveui", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "DispositivoQueue no encontrado"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> getOne(
            @ApiParam(value = "deveui del dispositivo", required = true) @PathVariable("deveui") String deveui
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "DispisitivoQueue encontrado", dispositivoQueueTempService.findByDeveui(deveui).get()), HttpStatus.OK);
    }

    @PostMapping("/{usuarioId}/{deveui}")
    @ApiOperation(value = "Guardar un dato en la cola para proceder a comunicar con el dispositivo", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 202, message = "DispositivoQueue creado exitosamente"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creación del DispositivoQueue"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> save(
            @ApiParam(value = "id del usuario", required = true) @PathVariable("usuarioId") Integer usuarioId,
            @ApiParam(value = "Deveui del dispositivo", required = true) @PathVariable("deveui") String deveui,
            @Valid @ApiParam(value = "Información del estatus", required = true) @RequestBody DispositivoQueueTempDTO dispositivoQueueTempDTO
    ) throws ParseException {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "DispisitivoQueue creado", dispositivoQueueTempService.save(dispositivoQueueTempDTO, usuarioId, deveui)), HttpStatus.CREATED);
    }

    @PostMapping("/backEncolarMedidor/{usuarioId}/{deveui}")
    @ApiOperation(value = "Vuelve un encolar de un medidor de la zona para proceder a comunicar con el dispositivo", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 202, message = "ZonaQueue encontrado para volver a encolar"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creación del ZonaQueue"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> backEncolarMedidor(
            @ApiParam(value = "Id de la usuario", required = true) @PathVariable("usuarioId") Integer usuarioId,
            @ApiParam(value = "Deveui del dispositivo", required = true) @PathVariable("deveui") String deveui,
            @Valid @ApiParam(value = "Información de la ZonaQueue", required = true) @RequestBody DispositivoQueueTempDTO dispositivoQueueTempDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "DispisitivoQueue encolado correctamente", dispositivoQueueTempService.backEncolarMedidor(dispositivoQueueTempDTO, usuarioId, deveui)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{deveui}")
    @ApiOperation(value = "Elimina un DispositivoQueue de la cola", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "DispositivoQueue no encontrado para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> delete(
            @ApiParam(value = "Deveui del dispositivo", required = true) @PathVariable("deveui") String deveui
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "DispositivoQueue eliminado", dispositivoQueueTempService.delete(deveui)), HttpStatus.OK);
    }

    @DeleteMapping("/deleteByDeveui/{deveui}")
    @ApiOperation(value = "Elimina un DispositivoQueue de la cola por deveui", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "DispositivoQueue no encontrado para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> deleteByDeveui(
            @ApiParam(value = "Deveui del dispositivo", required = true) @PathVariable("deveui") String deveui
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "DispositivoQueue eliminado", dispositivoQueueTempService.deleteByDeveui(deveui)), HttpStatus.OK);
    }
}
