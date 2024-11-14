package com.indra.carrito.dto;

import com.indra.carrito.entities.CarritoProducto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class CarritoDTO {


    private List<ProductoDTO> productos;
    private BigDecimal descuento;
    private BigDecimal total;
    private BigDecimal totalConDescuento;

}

