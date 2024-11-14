package com.indra.carrito.controller;

import com.indra.carrito.dto.ExceptionResponseDto;
import com.indra.carrito.entities.Producto;
import com.indra.carrito.services.ProductoService;
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
@Tag(name = "Carrito de compras", description = "La API permitira la creacion de productos.")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/crear")
    @Operation(description = "Crea un nuevo producto con su nombre identificativo y el precio del producto.",
            summary = "Crear un nuevo cupon")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion Exitosa", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "406", description = "Validaciones de entrada de parametros", content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content(schema = @Schema(implementation = ExceptionResponseDto.class)))
    })
    public ResponseEntity<String> crearProducto(@Valid @RequestBody Producto producto){
        return ResponseEntity.ok(productoService.crearProducto(producto));
    }

}
