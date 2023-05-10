package com.telcom.ups.monolitico.detalle.controller;

import com.telcom.ups.monolitico.detalle.service.DetalleService;
import org.springframework.beans.factory.annotation.Autowired;

//@Api(description = "REST API relacionado a la entidad de Detalle", tags = "Detalles de Factura")
//@RestController
//@RequestMapping("/detalle")
public class DetalleController {

    @Autowired
    private DetalleService detalleService;

//    @PostMapping("/")
//    @ApiOperation(value = "Guarda un Detalle según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
//    @ApiResponses({
//            @ApiResponse(code = 202, message = "Detalle creado exitosamente"),
//            @ApiResponse(code = 400, message = "Error de validación // Error en la creación del Detalle"),
//            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
//    })
//    public ResponseEntity<Response> save(@Valid @ApiParam(value = "Información del detalle", required = true) @RequestBody DetalleDTO detalleDTO) {
//        Response response;
//        try {
//            response = new Response(HttpStatus.CREATED.toString(), "Detalle creado", detalleService.save(detalleDTO));
//            return new ResponseEntity<>(response, HttpStatus.CREATED);
//        } catch (ConstraintViolationException ex) {
//            List<String> errors = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(java.util.stream.Collectors.toList());
//            response = new Response(HttpStatus.BAD_REQUEST.toString(), "Error de validación", errors);
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            //System.out.println("EXCEPTION");
//            response = new Response(HttpStatus.BAD_REQUEST.toString(), "Error en la creación del Detalle", e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    @ApiOperation(value = "Elimina un Detalle por su id", authorizations = {@Authorization(value = "JWT")})
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "OK"),
//            @ApiResponse(code = 404, message = "Detalle no encontrado para eliminar"),
//            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
//    })
//    public ResponseEntity<Response> delete(@ApiParam(value = "ID del detalle", required = true) @PathVariable("id") Integer id) {
//        Response response;
//        try {
//            if (id == 0) {
//                return new ResponseEntity<>(new Response(HttpStatus.NOT_FOUND.toString(), "Id debe ser diferente de 0", false), HttpStatus.NOT_FOUND);
//            }
//            boolean deleteResponse = detalleService.delete(id);
//            if (deleteResponse) {
//                return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Detalle eliminado", true), HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(new Response(HttpStatus.NOT_FOUND.toString(), "Detalle no encontrado para eliminar", false), HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            response = new Response(HttpStatus.BAD_REQUEST.toString(), "Error al eliminar el Detalle", e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//    }
}
