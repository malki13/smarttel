package com.telcom.ups.monolitico.detallepago.controller;

//@Api(description = "REST API relacionado a la entidad de DetallePago", tags = "Pagos de Factura")
//@RestController
//@RequestMapping("/detallepago")
public class DetallePagoController {

//    @Autowired
//    private DetallePagoService detallePagoService;
//
//    @PostMapping("/")
//    @ApiOperation(value = "Guarda un DetallePago según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
//    @ApiResponses({
//            @ApiResponse(code = 202, message = "DetallePago creado exitosamente"),
//            @ApiResponse(code = 400, message = "Error de validación // Error en la creación del DetallePago"),
//            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
//    })
//    public ResponseEntity<Response> save(@Valid @ApiParam(value = "Información del pago", required = true) @RequestBody DetallePagoDTO detallePagoDTO) {
//        Response response;
//        try {
//            response = new Response(HttpStatus.CREATED.toString(), "DetallePago creado", detallePagoService.save(detallePagoDTO));
//            return new ResponseEntity<>(response, HttpStatus.CREATED);
//        } catch (ConstraintViolationException ex) {
//            List<String> errors = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(java.util.stream.Collectors.toList());
//            response = new Response(HttpStatus.BAD_REQUEST.toString(), "Error de validación", errors);
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            //System.out.println("EXCEPTION");
//            response = new Response(HttpStatus.BAD_REQUEST.toString(), "Error en la creación del DetallePago", e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    @ApiOperation(value = "Elimina un DetallePago por su id", authorizations = {@Authorization(value = "JWT")})
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "OK"),
//            @ApiResponse(code = 404, message = "DetallePago no encontrado para eliminar"),
//            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
//    })
//    public ResponseEntity<Response> delete(@ApiParam(value = "ID del pago", required = true) @PathVariable("id") Integer id) {
//        Response response;
//        try {
//            if (id == 0) {
//                return new ResponseEntity<>(new Response(HttpStatus.NOT_FOUND.toString(), "Id debe ser diferente de 0", false), HttpStatus.NOT_FOUND);
//            }
//            boolean deleteResponse = detallePagoService.delete(id);
//            if (deleteResponse) {
//                return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "DetallePago eliminado", true), HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(new Response(HttpStatus.NOT_FOUND.toString(), "DetallePago no encontrado para eliminar", false), HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            response = new Response(HttpStatus.BAD_REQUEST.toString(), "Error al eliminar el DetallePago", e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//    }
}
