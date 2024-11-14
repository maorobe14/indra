package com.indra.carrito.controller;

import com.indra.carrito.dto.CarritoDTO;
import com.indra.carrito.dto.ExceptionResponseDto;
import com.indra.carrito.entities.Carrito;
import com.indra.carrito.exceptions.DatosException;
import com.indra.carrito.services.CarritoService;
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
@Tag(name = "Carrito de compras", description = "La API permitira agregar productos a su carrito, actualizar cantidades, eliminar productos y obtener productos asociados al carrito.")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @PostMapping("/{carritoId}/producto/{productoId}")
    @Operation(description = "Agrega un producto al carrito especificado con una cantidad determinada. Requiere el ID del carrito y del producto y la cantidad del producto.",
            summary = "Agregar producto al carrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion Exitosa", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Errores de negocio", content = @Content(schema = @Schema(implementation = DatosException.class))),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content(schema = @Schema(implementation = ExceptionResponseDto.class)))
    })
    public ResponseEntity<String> agregarProducto(@PathVariable Long carritoId,
                                                  @PathVariable Long productoId,
                                                  @RequestParam int cantidad) throws DatosException {

        return ResponseEntity.ok(carritoService.agregarProducto(carritoId, productoId, cantidad));
    }

    @DeleteMapping("/{carritoId}/producto/{productoId}")
    @Operation(description = "Elimina un producto específico del carrito indicado. Requiere el ID del carrito y del producto.",
            summary = "Eliminar producto del carrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion Exitosa", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Errores de negocio", content = @Content(schema = @Schema(implementation = DatosException.class))),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content(schema = @Schema(implementation = ExceptionResponseDto.class)))
    })
    public ResponseEntity<String> eliminarProducto(@PathVariable Long carritoId,
                                                   @PathVariable Long productoId) throws DatosException {

        return ResponseEntity.ok(carritoService.eliminarProducto(carritoId, productoId));
    }

    @PutMapping("/{carritoId}/producto/{productoId}")
    @Operation(description = "Actualiza la cantidad de un producto existente en el carrito. Requiere el ID del carrito, el ID del producto, y la cantidad a actualizar.",
            summary = "Actualizar cantidad de producto en el carrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion Exitosa", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Errores de negocio", content = @Content(schema = @Schema(implementation = DatosException.class))),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content(schema = @Schema(implementation = ExceptionResponseDto.class)))
    })
    public ResponseEntity<String> actualizarCantidad(@PathVariable Long carritoId,
                                                     @PathVariable Long productoId,
                                                     @RequestParam int cantidad) throws DatosException {

        return ResponseEntity.ok(carritoService.actualizarCantidad(carritoId, productoId, cantidad));
    }

    @GetMapping("/{carritoId}")
    @Operation(
            description = "Obtiene los detalles de un carrito, aplicando un descuento si se proporciona un código de cupón. Requiere el ID del carrito y opcionalmente el código del cupón.",
            summary = "Ver carrito con o sin descuento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion Exitosa", content = @Content(schema = @Schema(implementation = CarritoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Errores de negocio", content = @Content(schema = @Schema(implementation = DatosException.class))),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content(schema = @Schema(implementation = ExceptionResponseDto.class)))
    })
    public ResponseEntity<CarritoDTO> verCarrito(@PathVariable Long carritoId,
                                                 @RequestParam(required = false) String codigoCupon) throws DatosException {
        CarritoDTO carritoDTO = carritoService.verCarritoConDescuento(carritoId, codigoCupon);
        return ResponseEntity.ok(carritoDTO);
    }

    @PostMapping("/crear")
    @Operation(description = "Crea un nuevo carrito con los productos y cantidades especificados en el cuerpo de la solicitud.",
            summary = "Crear un nuevo carrito")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operacion Exitosa", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "406", description = "Validaciones de entrada de parametros", content = @Content(schema = @Schema(implementation = Map.class))),
            @ApiResponse(responseCode = "500", description = "Error del servidor", content = @Content(schema = @Schema(implementation = ExceptionResponseDto.class)))
    })
    public ResponseEntity<String> crearCarrito(@Valid @RequestBody Carrito carrito) {
        return ResponseEntity.ok(carritoService.crearCarrito(carrito));
    }
}
