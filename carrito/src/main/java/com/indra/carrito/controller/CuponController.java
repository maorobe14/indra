package com.indra.carrito.controller;

import com.indra.carrito.dto.ExceptionResponseDto;
import com.indra.carrito.entities.Cupon;
import com.indra.carrito.services.CuponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Tag(name = "Carrito de compras", description = "La API permitira la creacion de cupones.")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping("/api/cupon")
public class CuponController {

    @Autowired
    private CuponService cuponService;

    @PostMapping("/crear")
    @Operation(description = "Crea un nuevo cupon con su codigo identificativo y el valor del bono, cantidades especificados en el cuerpo de la solicitud.",
            summary = "Crear un nuevo cupon")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion Exitosa", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "406", description = "Validaciones de entrada de parametros", content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content(schema = @Schema(implementation = ExceptionResponseDto.class)))
    })
    public ResponseEntity<String> crearCupon(@Valid @RequestBody Cupon cupon){
        return ResponseEntity.ok(cuponService.crearCupon(cupon));
    }

}
