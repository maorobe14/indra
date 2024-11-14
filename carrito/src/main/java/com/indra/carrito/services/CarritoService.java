package com.indra.carrito.services;

import com.indra.carrito.dto.CarritoDTO;
import com.indra.carrito.entities.Carrito;
import com.indra.carrito.exceptions.DatosException;

import java.math.BigDecimal;

public interface CarritoService {

    String crearCarrito(Carrito carrito);

    String agregarProducto(Long carritoId, Long productoId, int cantidad) throws DatosException;

    String eliminarProducto(Long carritoId, Long productoId) throws DatosException;

    String actualizarCantidad(Long carritoId, Long productoId, int cantidad) throws DatosException;

    CarritoDTO verCarritoConDescuento(Long carritoId, String codigoCupon) throws DatosException;
    BigDecimal aplicarCupon(Carrito carrito, String codigoCupon) throws DatosException;
}