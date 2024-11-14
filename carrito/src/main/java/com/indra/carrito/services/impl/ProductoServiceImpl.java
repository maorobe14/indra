package com.indra.carrito.services.impl;

import com.indra.carrito.entities.Producto;
import com.indra.carrito.repositories.ProductoRepository;
import com.indra.carrito.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Override
    public String crearProducto(Producto producto) {
        productoRepository.save(producto);
        return "¡Producto creado con exito!";
    }
}
