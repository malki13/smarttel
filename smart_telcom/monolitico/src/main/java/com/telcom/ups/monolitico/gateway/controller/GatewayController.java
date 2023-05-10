package com.telcom.ups.monolitico.gateway.controller;

import com.telcom.ups.data.dto.GatewayDTO;
import com.telcom.ups.data.info.GatewayInfo;
import com.telcom.ups.monolitico.gateway.service.GatewayService;
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
import java.util.List;

@Api(description = "REST API relacionado a la entidad de Gateway", tags = "Gateways")
@RestController
@RequestMapping("/gestion/gateway")
public class GatewayController {

    @Autowired
    private GatewayService gatewayService;

    @GetMapping("/")
    @ApiOperation(value = "Obtiene toda la lista de Gateways", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<GatewayInfo>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(gatewayService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{gatewayId}")
    @ApiOperation(value = "Obtiene un Gateway por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Gateway no encontrado"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> getOne(
            @ApiParam(value = "ID del gateway", required = true) @PathVariable("gatewayId") Integer gatewayId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Gateway encontrado", gatewayService.getOne(gatewayId).get()), HttpStatus.OK);
    }

    @PostMapping("/{empresaId}")
    @ApiOperation(value = "Guarda un Gateway según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 202, message = "Gateway creado exitosamente"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creación del Gateway"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> save(
            @ApiParam(value = "ID de la empresa", required = true) @PathVariable("empresaId") Integer empresaId,
            @Valid @ApiParam(value = "Información del gateway", required = true) @RequestBody GatewayDTO gatewayDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "Gateway creado", gatewayService.save(empresaId, gatewayDTO)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{gatewayId}")
    @ApiOperation(value = "Elimina un Gateway por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Gateway no encontrado para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> delete(
            @ApiParam(value = "ID del gateway", required = true) @PathVariable("gatewayId") Integer gatewayId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Gateway eliminado", gatewayService.delete(gatewayId)), HttpStatus.OK);
    }

    @PutMapping("/{empresaId}/{gatewayId}")
    @ApiOperation(value = "Actualiza un Gateway por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Gateway no encontrado para actualizar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> update(
            @ApiParam(value = "ID de la empresa", required = true) @PathVariable("empresaId") Integer empresaId,
            @Valid @ApiParam(value = "Información del gateway", required = true) @RequestBody GatewayDTO gatewayDTO,
            @ApiParam(value = "ID del gateway", required = true) @PathVariable("gatewayId") Integer gatewayId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Gateway actualizado", gatewayService.update(empresaId, gatewayDTO, gatewayId)), HttpStatus.OK);
    }

    @GetMapping("/searchByNombre")
    @ApiOperation(value = "Obtiene una la lista de Gateways por su nombre", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<List<GatewayInfo>> findByNombre(
            @ApiParam(value = "Nombre del gateway", required = true) @RequestParam String nombre
    ) {
        return new ResponseEntity<>(gatewayService.searchByNombre(nombre), HttpStatus.OK);
    }

    @GetMapping("/searchById")
    @ApiOperation(value = "Obtiene una la lista de Gateways por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<List<GatewayInfo>> findById(@RequestParam String id) {
        return new ResponseEntity<>(gatewayService.searchById(id), HttpStatus.OK);
    }

    @GetMapping("/getAllByEmpresa")
    @ApiOperation(value = "Obtiene toda la lista de Gateways por empresa", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<GatewayInfo>> getAllByEmpresa(
            @ApiParam(value = "ID de la empresa", required = true) @RequestParam Integer idEmpresa,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        return new ResponseEntity<>(gatewayService.getAllByEmpresa(idEmpresa, pageable), HttpStatus.OK);
    }

//    @GetMapping("/getAllByIntegracion")
//    @ApiOperation(value = "Obtiene toda la lista de Gateways por integracion", authorizations = {@Authorization(value = "JWT")})
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "OK"),
//            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
//    })
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "offset", dataType = "integer", paramType = "query",
//                    value = "Indica la distancia (desplazamiento) desde el inicio del objeto hasta un punto o elemento dado"),
//            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", readOnly = true,
//                    value = "Numero de Página que desea recuperar (0..N)"),
//            @ApiImplicitParam(name = "paged", dataType = "boolean", paramType = "query",
//                    value = "Indica si se aplica o no se aplica la paginación al resultado de la consulta"),
//            @ApiImplicitParam(name = "pageNumber", dataType = "integer", paramType = "query",
//                    value = "Numero de Página que desea recuperar (0..N)"),
//            @ApiImplicitParam(name = "pageSize", dataType = "integer", paramType = "query",
//                    value = "Número de registros por página"),
//            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", readOnly = true,
//                    value = "Número de registros por página"),
//            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
//                    value = "Criterios de orden en el formato: propiedad (, asc | desc). " +
//                            "El orden de clasificación predeterminado es ascendente. " +
//                            "Se admiten varios criterios de clasificación."),
//            @ApiImplicitParam(name = "sort.sorted", dataType = "boolean", paramType = "query",
//                    value = "Indica si se aplica o no se aplica el orden a la clasificación del resultado de la consulta"),
//            @ApiImplicitParam(name = "sort.unsorted", dataType = "boolean", paramType = "query",
//                    value = "Indica si se aplica o no se aplica el desorden a la clasificación del resultado de la consulta"),
//            @ApiImplicitParam(name = "unpaged", dataType = "boolean", paramType = "query",
//                    value = "Indica si se aplica o no se aplica que el resultado de la consulta no se paginado")
//    })
//    public ResponseEntity<Page<GatewayInfo>> getAllByIntegracion(
//            @ApiParam(value = "ID de la empresa", required = true) @RequestParam Integer idIntegracion,
//            @PageableDefault(size = 10, page = 0) Pageable pageable
//    ) {
//        return new ResponseEntity<>(gatewayService.getAllByIntegracion(idIntegracion, pageable), HttpStatus.OK);
//    }

}
