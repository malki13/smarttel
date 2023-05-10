package com.telcom.ups.monolitico.application.controller;

import com.telcom.ups.data.dto.ApplicationDTO;
import com.telcom.ups.data.info.ApplicationInfo;
import com.telcom.ups.monolitico.application.service.ApplicationService;
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

@Api(description = "REST API relacionado a la entidad de Application", tags = "Applications")
@RestController
@RequestMapping("/gestion/application")
public class ApplicationController {


    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/")
    @ApiOperation(value = "Obtiene toda la lista de Applications", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<ApplicationInfo>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(applicationService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{applicationId}")
    @ApiOperation(value = "Obtiene un Application por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Application no encontrado"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> getOne(
            @ApiParam(value = "ID del application", required = true) @PathVariable("applicationId") Integer applicationId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Application encontrado", applicationService.getOne(applicationId).get()), HttpStatus.OK);
    }

    @PostMapping("/")
    @ApiOperation(value = "Guarda un Application según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 202, message = "Application creado exitosamente"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creacion del Application"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> save(
            @Valid @ApiParam(value = "Información del application", required = true) @RequestBody ApplicationDTO applicationDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "Application creado", applicationService.save(applicationDTO)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{applicationId}")
    @ApiOperation(value = "Elimina un Application por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Application no encontrado para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> delete(@ApiParam(value = "ID del application", required = true) @PathVariable("applicationId") Integer applicationId) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Application eliminado", applicationService.delete(applicationId)), HttpStatus.OK);
    }

    @PutMapping("/{applicationId}")
    @ApiOperation(value = "Actualiza un Application por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Application no encontrado para actualizar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> update(
            @Valid @ApiParam(value = "Información del application", required = true) @RequestBody ApplicationDTO applicationDTO,
            @ApiParam(value = "ID del application", required = true) @PathVariable("applicationId") Integer applicationId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Application actualizado", applicationService.update(applicationDTO, applicationId)), HttpStatus.OK);
    }

//    @GetMapping("/getAllByPerfilServicio")
//    @ApiOperation(value = "Obtiene toda la lista de Applications por gateway", authorizations = {@Authorization(value = "JWT")})
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
//    public ResponseEntity<Page<ApplicationInfo>> getAllByPerfilServicio(
//            @ApiParam(value = "ID del PerfilServicio", required = true) @RequestParam Integer idPerfilServicio,
//            @PageableDefault(size = 10, page = 0) Pageable pageable
//    ) {
//        return new ResponseEntity<>(applicationService.getAllByPerfilServicio(idPerfilServicio, pageable), HttpStatus.OK);
//    }

    @GetMapping("/getAllByEmpresa")
    @ApiOperation(value = "Obtiene toda la lista de Applications por empresa", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<ApplicationInfo>> getAllByEmpresa(
            @ApiParam(value = "ID de la Empresa", required = true) @RequestParam Integer idEmpresa,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        return new ResponseEntity<>(applicationService.getAllByEmpresa(idEmpresa, pageable), HttpStatus.OK);
    }

    @GetMapping("/getApplicationsByEmpresaProtocolo")
    @ApiOperation(value = "Obtiene el listado de aplicaciones por empresa y protocolo", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<List<ApplicationInfo>> findByEmpresaProtocolo(
            @ApiParam(value = "id de la empresa", required = true) @RequestParam Integer idEmpresa,
            @ApiParam(value = "id del protocolo", required = true) @RequestParam Integer idProtocoloTipo
    ) {
        return new ResponseEntity<>(applicationService.getApplicationsByEmpresaProtocolo(idEmpresa, idProtocoloTipo), HttpStatus.OK);
    }

}
