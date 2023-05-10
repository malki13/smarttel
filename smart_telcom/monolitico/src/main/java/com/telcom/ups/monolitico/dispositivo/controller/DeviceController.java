package com.telcom.ups.monolitico.dispositivo.controller;

import com.telcom.ups.data.dto.DetalleComunicacionDTO;
import com.telcom.ups.data.dto.DispositivoDTO;
import com.telcom.ups.data.dto.ReporteComunicacionDTO;
import com.telcom.ups.data.info.DispositivoInfoOnly;
import com.telcom.ups.monolitico.dispositivo.service.DeviceService;
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

@Api(description = "REST API relacionado a la entidad de Dispositivo", tags = "Dispositivos")
@RestController
@RequestMapping("/gestion/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/")
    @ApiOperation(value = "Obtiene toda la lista de Dispositivos", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<DispositivoInfoOnly>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(deviceService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un Dispositivo por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Dispositivo no encontrado"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> getOne(@ApiParam(value = "ID del dispositivo", required = true) @PathVariable("id") Integer id) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Dispositivo encontrado", deviceService.getOne(id).get()), HttpStatus.OK);
    }

    @GetMapping("/deveui/{deveui}")
    @ApiOperation(value = "Obtiene un Dispositivo por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Dispositivo no encontrado"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> getDeveui(
            @ApiParam(value = "Deveui del dispositivo", required = true) @PathVariable("deveui") String deveui
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Dispositivo encontrado", deviceService.getDeveui(deveui).get()), HttpStatus.OK);
    }

    @PostMapping("/")
    @ApiOperation(value = "Guarda un Dispositivo según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 202, message = "Dispositivo creado exitosamente"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creacion del Dispositivo"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> save(
            @Valid @ApiParam(value = "Información del dispositivo", required = true) @RequestBody DispositivoDTO dispositivoDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "Dispositivo creado", deviceService.save(dispositivoDTO)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Elimina un Dispositivo por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 404, message = "Dispositivo no encontrado para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> delete(
            @ApiParam(value = "ID del dispositivo", required = true) @PathVariable("id") Integer id
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Dispositivo eliminado", deviceService.delete(id)), HttpStatus.OK);
    }

    @PostMapping("/updatePropietario/{clienteId}/{dispositivoId}")
    @ApiOperation(value = "Actualiza el propietario del Dispositivo por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Dispositivo no encontrado para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> updatePropietario(
            @ApiParam(value = "ID del cliente", required = true) @PathVariable("clienteId") Integer clienteId,
            @ApiParam(value = "ID del dispositivo", required = true) @PathVariable("dispositivoId") Integer dispositivoId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Propietario actualizado", deviceService.updatePropietario(clienteId, dispositivoId)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Actualiza un Dispositivo por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Dispositivo no encontrado para actualizar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> update(
            @Valid @ApiParam(value = "Información del dispositivo", required = true) @RequestBody DispositivoDTO dispositivoDTO,
            @ApiParam(value = "ID del dispositivo", required = true) @PathVariable("id") Integer id
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Dispositivo actualizado", deviceService.update(dispositivoDTO, id)), HttpStatus.OK);
    }

    @PutMapping("/updateDetalleComunicacion/{deveui}")
    @ApiOperation(value = "Actualiza el Detalle de la comunicacion por deveui", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Dispositivo no encontrado para actualizar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> updateDetalleComunicacion(
            @Valid @ApiParam(value = "Información del dispositivo", required = true) @RequestBody DetalleComunicacionDTO detalleComunicacionDTO,
            @ApiParam(value = "Deveui del dispositivo", required = true) @PathVariable("deveui") String deveui
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "DetalleComunicacion actualizado", deviceService.updateDetalleComunicacion(detalleComunicacionDTO, deveui)), HttpStatus.OK);
    }

    @PutMapping("/updateReporteComunicacion/{deveui}")
    @ApiOperation(value = "Actualiza el Reporte de la comunicacion por deveui", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Dispositivo no encontrado para actualizar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> updateReporteComunicacion(
            @Valid @ApiParam(value = "Información del reporte", required = true) @RequestBody ReporteComunicacionDTO reporteComunicacionDTO,
            @ApiParam(value = "Deveui del dispositivo", required = true) @PathVariable("deveui") String deveui
    ) {
        return new ResponseEntity<>(new Response(
                HttpStatus.OK.toString(), "ReporteComunicacion actualizado",
                deviceService.updateReporteComunicacion(reporteComunicacionDTO, deveui)),
                HttpStatus.OK
        );
    }

    @GetMapping("/getAllByApplication")
    @ApiOperation(value = "Obtiene toda la lista de Dispositivos por application", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<DispositivoInfoOnly>> getAllByApplication(@ApiParam(value = "ID del application", required = true) @RequestParam Integer idApplication, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(deviceService.getAllByApplication(idApplication, pageable), HttpStatus.OK);
    }

    @GetMapping("/getAllByCliente")
    @ApiOperation(value = "Obtiene toda la lista de Dispositivos por cliente", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<DispositivoInfoOnly>> getAllByCliente(@ApiParam(value = "ID del cliente", required = true) @RequestParam Integer idCliente, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(deviceService.getAllByCliente(idCliente, pageable), HttpStatus.OK);
    }

    @GetMapping("/getAllByZona")
    @ApiOperation(value = "Obtiene toda la lista de Dispositivos por zona", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<DispositivoInfoOnly>> getAllByZona(
            @ApiParam(value = "ID de la zona", required = true) @RequestParam Integer idZona,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(deviceService.getAllByZona(idZona, pageable), HttpStatus.OK);
    }

    @GetMapping("/getAllByEmpresa")
    @ApiOperation(value = "Obtiene toda la lista de Dispositivos por empresa", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<DispositivoInfoOnly>> getAllByEmpresa(
            @ApiParam(value = "ID de la empresa", required = true) @RequestParam Integer idEmpresa,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(deviceService.getAllByEmpresa(idEmpresa, pageable), HttpStatus.OK);
    }

    @GetMapping("/getAllByTipoEmpZonProt")
    @ApiOperation(value = "Obtiene toda la lista de Dispositivos por empresa, zona, tipo y protocolo", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<DispositivoInfoOnly>> getAllByTipoEmpZonProt(
            @ApiParam(value = "ID de la empresa", required = true) @RequestParam Integer idEmpresa,
            @ApiParam(value = "ID de la tipo", required = true) @RequestParam Integer idTipo,
            @ApiParam(value = "ID de la zona", required = true) @RequestParam Integer idZona,
            @ApiParam(value = "ID del protocolo", required = true) @RequestParam Integer idProtocolo,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(deviceService.getAllByTipoEmpZonProt(idEmpresa, idTipo, idZona, idProtocolo, pageable), HttpStatus.OK);
    }

    @GetMapping("/getAllByCienteProtocoloEmpresa")
    @ApiOperation(value = "Obtiene toda la lista de Dispositivos por cliente y protocolo", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<DispositivoInfoOnly>> getAllByCienteProtocoloEmpresa(
            @ApiParam(value = "Codigo del cliente", required = true) @RequestParam String codCliente,
            @ApiParam(value = "ID del protocolo", required = true) @RequestParam Integer idProtocolo,
            @ApiParam(value = "ID de la empresa", required = true) @RequestParam Integer idEmpresa,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(deviceService.getAllByCienteProtocoloEmpresa(codCliente, idProtocolo, idEmpresa, pageable), HttpStatus.OK);
    }
}
