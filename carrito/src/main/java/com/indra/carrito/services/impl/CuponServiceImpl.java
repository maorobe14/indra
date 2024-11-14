package com.indra.carrito.services.impl;

import com.indra.carrito.entities.Cupon;
import com.indra.carrito.repositories.CuponRepository;
import com.indra.carrito.services.CuponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuponServiceImpl implements CuponService {
    @Autowired
    private CuponRepository cuponRepository;

    @Override
    public String crearCupon(Cupon cupon) {
        cuponRepository.save(cupon);
        return "¡Cupon creado con Exito!";
    }
}
