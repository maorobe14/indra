package com.indra.carrito.exceptions;

import com.indra.carrito.dto.ExceptionResponseDto;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Builder
@Data
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DatosException extends Exception {
    private ExceptionResponseDto exceptionResponseObj;
}
