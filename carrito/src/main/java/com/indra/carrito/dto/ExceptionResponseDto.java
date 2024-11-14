package com.indra.carrito.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResponseDto {
    private int codigo;
    private String descripcion;
    private String categoria;
    private String path;
}
