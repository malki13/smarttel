package com.telcom.ups.monolitico.anexo.controller;

import com.telcom.ups.monolitico.anexo.service.AnexoService;
import org.springframework.beans.factory.annotation.Autowired;

//@Api(description = "REST API relacionado a la entidad de Anexo", tags = "Anexos")
//@RestController
//@RequestMapping("/anexo")
public class AnexoController {

    @Autowired
    private AnexoService anexoService;

//    @GetMapping("/")
//    @ApiOperation(value = "Obtiene toda la lista de Anexos", authorizations = {@Authorization(value = "JWT")})
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "OK"),
//            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
//    })
//    public ResponseEntity<Page<AnexoDTO>> findAll(@PageableDefault(size = 10, page = 0) Pageable pageable) {
//        return new ResponseEntity<>(anexoService.findAll(pageable), HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    @ApiOperation(value = "Obtiene un Anexo por su id", authorizations = {@Authorization(value = "JWT")})
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "OK"),
//            @ApiResponse(code = 404, message = "Anexo no encontrada"),
//            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
//    })
//    public ResponseEntity<Response> getOne(@PathVariable("id") Integer id) {
//        Response response;
//        try {
//            if (id == 0) {
//                return new ResponseEntity<>(new Response(HttpStatus.NOT_FOUND.toString(), "Id debe ser diferente de 0", false), HttpStatus.NOT_FOUND);
//            }
//            boolean anexoDto = anexoService.getOne(id).isPresent();
//            if (anexoDto) {
//                response = new Response(HttpStatus.OK.toString(), "Anexo encontrado", anexoService.getOne(id).get());
//                return new ResponseEntity<>(response, HttpStatus.OK);
//            } else {
//                response = new Response(HttpStatus.NOT_FOUND.toString(), "Anexo no encontrado", null);
//                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            response = new Response(HttpStatus.BAD_REQUEST.toString(), "Error al obtener el Anexo", e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//    }

//    @PostMapping("/")
//    @ApiOperation(value = "Guarda un Anexo según los parametros correspondientes", authorizations = {@Authorization(value = "JWT")})
//    @ApiResponses({
//            @ApiResponse(code = 202, message = "Anexo creado exitosamente"),
//            @ApiResponse(code = 400, message = "Error de validación // Error en la creación del Anexo"),
//            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
//    })
//    public ResponseEntity<Response> save(@Valid @ApiParam(value = "Información del anexo", required = true) @RequestBody AnexoDTO anexoDTO) {
//        Response response;
//        try {
//            response = new Response(HttpStatus.CREATED.toString(), "Anexo creado", anexoService.save(anexoDTO));
//            return new ResponseEntity<>(response, HttpStatus.CREATED);
//        } catch (ConstraintViolationException ex) {
//            List<String> errors = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(java.util.stream.Collectors.toList());
//            response = new Response(HttpStatus.BAD_REQUEST.toString(), "Error de validación", errors);
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            //System.out.println("EXCEPTION");
//            response = new Response(HttpStatus.BAD_REQUEST.toString(), "Error en la creación del Anexo", e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    @ApiOperation(value = "Elimina un Anexo por su id", authorizations = {@Authorization(value = "JWT")})
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "OK"),
//            @ApiResponse(code = 404, message = "Anexo no encontrado para eliminar"),
//            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
//    })
//    public ResponseEntity<Response> delete(@ApiParam(value = "ID del anexo", required = true) @PathVariable("id") Integer id) {
//        Response response;
//        try {
//            if (id == 0) {
//                return new ResponseEntity<>(new Response(HttpStatus.NOT_FOUND.toString(), "Id debe ser diferente de 0", false), HttpStatus.NOT_FOUND);
//            }
//            boolean deleteResponse = anexoService.delete(id);
//            if (deleteResponse) {
//                return new ResponseEntity<>(new Response(HttpStatus.OK.toString(), "Anexo eliminado", true), HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(new Response(HttpStatus.NOT_FOUND.toString(), "Anexo no encontrado para eliminar", false), HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            response = new Response(HttpStatus.BAD_REQUEST.toString(), "Error al eliminar el Anexo", e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//    }

//    @PutMapping("/{id}")
//    @ApiOperation(value = "Actualiza un Anexo por su id", authorizations = {@Authorization(value = "JWT")})
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "OK"),
//            @ApiResponse(code = 404, message = "Anexo no encontrado para actualizar"),
//            @ApiResponse(code = 403, message = "No autorizado para consumir el servicio")
//    })
//    public ResponseEntity<Response> update(@RequestBody AnexoDTO anexoDTO, @PathVariable("id") Integer id) {
//        Response response;
//        try {
//            if (id == 0) {
//                return new ResponseEntity<>(new Response(HttpStatus.NOT_FOUND.toString(), "Id debe ser diferente de 0", false), HttpStatus.NOT_FOUND);
//            }
//            String resv = validaciones(anexoDTO);
//            if (resv != null) {
//                response = new Response(HttpStatus.NOT_FOUND.toString(), resv, null);
//                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//            }
//            AnexoDTO anexoDTOUpdated = anexoService.update(anexoDTO, id);
//            if (anexoDTOUpdated != null) {
//                response = new Response(HttpStatus.OK.toString(), "Anexo actualizado", anexoDTOUpdated);
//                return new ResponseEntity<>(response, HttpStatus.OK);
//            } else {
//                response = new Response(HttpStatus.NOT_FOUND.toString(), "Anexo no encontrado para actualizar", null);
//                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//            }
//        } catch (ConstraintViolationException ex) {
//            List<String> errors = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(java.util.stream.Collectors.toList());
//            response = new Response(HttpStatus.BAD_REQUEST.toString(), "Error de validación", errors);
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            response = new Response(HttpStatus.BAD_REQUEST.toString(), "Error al actualizar el Anexo", e.getCause().getCause().getLocalizedMessage());
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//        }
//    }

}
