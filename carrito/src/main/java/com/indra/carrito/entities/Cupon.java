package com.indra.carrito.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
@Entity
public class Cupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El codigo del producto no puede estar en blanco.")
    @Size(max = 15, message = "El nombre del codigo no puede tener más de 15 caracteres.")
    private String codigo;

    @NotNull(message = "El precio no puede ser nulo.")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0.")
    private BigDecimal descuento;

    @NotNull(message = "la fecha de vigencia no puede ser nula.")
    private LocalDate fechaVigenciaInicio;

    @NotNull(message = "la fecha de fin de vigencia no puede ser nula.")
    private LocalDate fechaVigenciaFin;


    private boolean utilizado;

}
