package com.telcom.ups.monolitico.factura.controller;

import com.telcom.ups.data.dto.FacturaDTO;
import com.telcom.ups.data.info.FacturaInfoOnly;
import com.telcom.ups.monolitico.factura.service.FacturaService;
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

@Api(description = "REST API relacionado a la entidad de Factura", tags = "Facturas")
@RestController
@RequestMapping("/facturacion/factura")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @GetMapping("/")
    @ApiOperation(value = "Obtiene toda la lista de Facturas", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<FacturaInfoOnly>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(facturaService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/getFacturasByClienteEmpresa")
    @ApiOperation(value = "Obtiene el listado de facturas por cliente y empresa", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<List<FacturaInfoOnly>> findByApellido(
            @ApiParam(value = "id del cliente", required = true) @RequestParam Integer idCliente,
            @ApiParam(value = "id de la empresa", required = true) @RequestParam Integer idEmpresa
    ) {
        return new ResponseEntity<>(facturaService.getFacturasByClienteEmpresa(idCliente, idEmpresa), HttpStatus.OK);
    }

    @GetMapping("/{facturaId}")
    @ApiOperation(value = "Obtiene una Factura por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Unidad no encontrada"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> getOne(
            @ApiParam(value = "ID de la factura", required = true) @PathVariable("facturaId") Integer facturaId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Factura encontrada", facturaService.getOne(facturaId).get()), HttpStatus.OK);
    }

    @PostMapping("/{empresaId}/{clienteId}")
    @ApiOperation(value = "Guarda una Factura según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 202, message = "Factura creada exitosamente"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creación de la Factura"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> save(
            @ApiParam(value = "ID de la empresa", required = true) @PathVariable("empresaId") Integer empresaId,
            @ApiParam(value = "ID del cliente", required = true) @PathVariable("clienteId") Integer clienteId,
            @Valid @ApiParam(value = "Información de la factura", required = true) @RequestBody FacturaDTO facturaDTO
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "Pre-Factura creada con exito", facturaService.save(empresaId, clienteId, facturaDTO)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{facturaId}")
    @ApiOperation(value = "Elimina una Factura por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Factura no encontrada para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<Response> delete(
            @ApiParam(value = "ID de la factura", required = true) @PathVariable("facturaId") Integer facturaId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Pre-Factura eliminada", facturaService.delete(facturaId)), HttpStatus.OK);
    }

//    @PutMapping("/{id}")
//    @ApiOperation(value = "Actualiza una Factura por su id", authorizations = {@Authorization(value = "JWT")})
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "OK"),
//            @ApiResponse(code = 404, message = "Factura no encontrada para actualizar"),
//            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
//    })
//    public ResponseEntity<Response> update(@RequestBody FacturaDTO facturaDTO, @PathVariable("id") Integer id) {
//        Response response;
//        try {
//            if (id == 0) {
//                return new ResponseEntity<>(new Response(HttpStatus.NOT_FOUND.toString(), "Id debe ser diferente de 0", false), HttpStatus.NOT_FOUND);
//            }
//            String resv = validaciones(facturaDTO);
//            if (resv != null) {
//                response = new Response(HttpStatus.NOT_FOUND.toString(), resv, null);
//                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//            }
//            FacturaDTO facturaDTOUpdated = facturaService.update(facturaDTO, id);
//            if (facturaDTOUpdated != null) {
//                response = new Response(HttpStatus.OK.toString(), "Factura actualizada", facturaDTOUpdated);
//                return new ResponseEntity<>(response, HttpStatus.OK);
//            } else {
//                response = new Response(HttpStatus.NOT_FOUND.toString(), "Factura no encontrada para actualizar", null);
//                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//            }
//        } catch (ConstraintViolationException ex) {
//            List<String> errors = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(java.util.stream.Collectors.toList());
//            response = new Response(HttpStatus.BAD_REQUEST.toString(), "Error de validación", errors);
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            response = new Response(HttpStatus.BAD_REQUEST.toString(), "Error al actualizar la Factura", e.getCause().getCause().getLocalizedMessage());
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//    }

    @GetMapping("/getAllByCliente")
    @ApiOperation(value = "Obtiene toda la lista de Facturas por cliente", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<FacturaInfoOnly>> getAllByCliente(@ApiParam(value = "ID del cliente", required = true) @RequestParam Integer idCliente, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(facturaService.getAllByCliente(idCliente, pageable), HttpStatus.OK);
    }

    @GetMapping("/getAllByEmpresa")
    @ApiOperation(value = "Obtiene toda la lista de Facturas por cliente", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<FacturaInfoOnly>> getAllByEmpresa(
            @ApiParam(value = "ID de la empresa", required = true) @RequestParam Integer idEmpresa,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(facturaService.getAllByEmpresa(idEmpresa, pageable), HttpStatus.OK);
    }

    @GetMapping("/getAllByEmpProtZon")
    @ApiOperation(value = "Obtiene toda la lista de Facturas por Empresa, Protocolo y Zona", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<FacturaInfoOnly>> getAllByEmpProtZon(
            @ApiParam(value = "ID de la empresa", required = true) @RequestParam Integer idEmpresa,
            @ApiParam(value = "ID del Protocolo", required = true) @RequestParam Integer idProtocolo,
            @ApiParam(value = "ID de la Zona", required = true) @RequestParam Integer idZona,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(facturaService.getAllByEmpProtZon(idEmpresa, idProtocolo, idZona, pageable), HttpStatus.OK);
    }

    @GetMapping("/getAllByEmpProtCli")
    @ApiOperation(value = "Obtiene toda la lista de Facturas por Empresa, Protocolo y Cliente", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<FacturaInfoOnly>> getAllByEmpProtCli(
            @ApiParam(value = "ID de la empresa", required = true) @RequestParam Integer idEmpresa,
            @ApiParam(value = "ID del Protocolo", required = true) @RequestParam Integer idProtocolo,
            @ApiParam(value = "ID del Cliente", required = true) @RequestParam Integer idCliente,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(facturaService.getAllByEmpProtCli(idEmpresa, idProtocolo, idCliente, pageable), HttpStatus.OK);
    }

}
