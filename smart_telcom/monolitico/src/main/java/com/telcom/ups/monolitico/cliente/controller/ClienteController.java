package com.telcom.ups.monolitico.cliente.controller;

import com.telcom.ups.data.info.ClienteInfoOnly;
import com.telcom.ups.data.read.EmpresaRead;
import com.telcom.ups.monolitico.cliente.service.ClienteService;
import com.telcom.ups.monolitico.util.response.Response;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(description = "REST API relacionado a la entidad de Cliente", tags = "Clientes")
@RestController
@RequestMapping("/gestion/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/")
    @ApiOperation(value = "Obtiene toda la lista de Clientes", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<ClienteInfoOnly>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
        return new ResponseEntity<>(clienteService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{clienteId}")
    @ApiOperation(value = "Obtiene un Cliente por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Cliente no encontrado"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> getOne(
            @ApiParam(value = "ID del cliente", required = true) @PathVariable("clienteId") Integer clienteId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Cliente encontrado", clienteService.getOne(clienteId).get()), HttpStatus.OK);
    }

    @GetMapping("/findByCodigo/{codigo}/{idEmpresa}")
    @ApiOperation(value = "Obtiene un Cliente por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Cliente no encontrado"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> getOneByEmpresaCodigo(
            @ApiParam(value = "Codigo del cliente", required = true) @PathVariable("codigo") String codigo,
            @ApiParam(value = "Id de la empresa", required = true) @PathVariable("idEmpresa") Integer idEmpresa
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Cliente encontrado", clienteService.getOneByEmpresaCodigo(codigo, idEmpresa).get()), HttpStatus.OK);
    }

    @GetMapping("/getEmpresasByCodigo")
    @ApiOperation(value = "Obtiene el listado de empresas a las cuales pertenece el cliente por el codigo del cliente", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
    })
    public ResponseEntity<List<EmpresaRead>> findByApellido(
            @ApiParam(value = "codigo del cliente", required = true) @RequestParam String codigo
    ) {
        return new ResponseEntity<>(clienteService.getEmpresasByCodigo(codigo), HttpStatus.OK);
    }

    @PostMapping("/{interventorId}/{estatusId}/{empresaId}")
    @ApiOperation(value = "Guarda un Cliente según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 202, message = "Cliente creado exitosamente"),
            @ApiResponse(code = 400, message = "Error de validación // Error en la creacion del Cliente"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> save(
            @ApiParam(value = "ID del interventor", required = true) @PathVariable("interventorId") Integer interventorId,
            @ApiParam(value = "ID del estatus", required = true) @PathVariable("estatusId") Integer estatusId,
            @ApiParam(value = "ID del empresa", required = true) @PathVariable("empresaId") Integer empresaId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.CREATED.toString(), "Cliente creado", clienteService.save(interventorId, estatusId, empresaId)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{clienteId}")
    @ApiOperation(value = "Elimina un Cliente por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Cliente no encontrado para eliminar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> delete(
            @ApiParam(value = "ID del cliente", required = true) @PathVariable("clienteId") Integer clienteId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Cliente eliminado", clienteService.delete(clienteId)), HttpStatus.OK);
    }

    @PutMapping("/{interventorId}/{estatusId}/{empresaId}/{clienteId}")
    @ApiOperation(value = "Actualiza un Cliente por su id", authorizations = {@Authorization(value = "JWT")})
    @ApiResponses({@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Cliente no encontrado para actualizar"),
            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")})
    public ResponseEntity<Response> update(
            @ApiParam(value = "ID del interventor", required = true) @PathVariable("interventorId") Integer interventorId,
            @ApiParam(value = "ID del estatus", required = true) @PathVariable("estatusId") Integer estatusId,
            @ApiParam(value = "ID del empresa", required = true) @PathVariable("empresaId") Integer empresaId,
            @ApiParam(value = "ID del cliente", required = true) @PathVariable("clienteId") Integer clienteId
    ) {
        return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Cliente actualizado", clienteService.update(interventorId, estatusId, empresaId, clienteId)), HttpStatus.OK);
    }

    @GetMapping("/getAllByEmpresa")
    @ApiOperation(value = "Obtiene toda la lista de Clientes por Empresa", authorizations = {@Authorization(value = "JWT")})
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
    public ResponseEntity<Page<ClienteInfoOnly>> getAllByEmpresa(
            @ApiParam(value = "ID de la empresa", required = true) @RequestParam Integer idEmpresa,
            @PageableDefault(size = 10, page = 0) Pageable pageable
    ) {
        return new ResponseEntity<>(clienteService.getAllByEmpresa(idEmpresa, pageable), HttpStatus.OK);
    }
}
