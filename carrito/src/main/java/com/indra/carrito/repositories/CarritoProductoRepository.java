package com.indra.carrito.repositories;

import com.indra.carrito.entities.CarritoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Long> {
    Optional<CarritoProducto> findByCarritoIdAndProductoId(Long carritoId, Long productoId);
}
