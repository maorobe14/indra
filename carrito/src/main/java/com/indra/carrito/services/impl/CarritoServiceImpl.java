package com.indra.carrito.services.impl;

import com.indra.carrito.dto.CarritoDTO;
import com.indra.carrito.dto.ExceptionResponseDto;
import com.indra.carrito.dto.ProductoDTO;
import com.indra.carrito.entities.Carrito;
import com.indra.carrito.entities.CarritoProducto;
import com.indra.carrito.entities.Cupon;
import com.indra.carrito.entities.Producto;
import com.indra.carrito.exceptions.DatosException;
import com.indra.carrito.repositories.CarritoProductoRepository;
import com.indra.carrito.repositories.CarritoRepository;
import com.indra.carrito.repositories.CuponRepository;
import com.indra.carrito.repositories.ProductoRepository;
import com.indra.carrito.services.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

    @Autowired
    private CuponRepository cuponRepository;


    public String crearCarrito(Carrito carrito){
        carritoRepository.save(carrito);
        return "¡Carrito creado con exito!";
    }


    public String agregarProducto(Long carritoId, Long productoId, int cantidad) throws DatosException{
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> crearExcepcion(1002,"Carrito no encontrado", "Error Carrito", "http://localhost:8080/api/carrito"));
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> crearExcepcion(2001,"Producto no encontrado en el carrito", "Error Producto", "http://localhost:8080/api/carrito"));


        CarritoProducto carritoProducto = carritoProductoRepository
                .findByCarritoIdAndProductoId(carritoId, productoId)
                .orElse(null);

        if (carritoProducto != null) {
            carritoProducto.setCantidad(carritoProducto.getCantidad() + cantidad);
            carritoProductoRepository.save(carritoProducto);
        } else {
            carritoProducto = new CarritoProducto();
            carritoProducto.setCarrito(carrito);
            carritoProducto.setProducto(producto);
            carritoProducto.setCantidad(cantidad);
            carritoProductoRepository.save(carritoProducto);
        }

        return "¡Se agrego el producto al carrito de forma exitosa!";
    }


    public String eliminarProducto(Long carritoId, Long productoId) throws DatosException {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> crearExcepcion(1002,"Carrito no encontrado", "Error Carrito", "http://localhost:8080/api/carrito"));
        CarritoProducto carritoProducto = carritoProductoRepository
                .findByCarritoIdAndProductoId(carritoId, productoId)
                .orElseThrow(() -> crearExcepcion(2001,"Producto no encontrado en el carrito", "Error Producto", "http://localhost:8080/api/carrito"));

        carritoProductoRepository.delete(carritoProducto);

        return "Se elimino el producto del carrito de compras";
    }


    public String actualizarCantidad(Long carritoId, Long productoId, int cantidad) throws DatosException{
        if (cantidad <= 0) {
            eliminarProducto(carritoId, productoId);
        } else {
            Carrito carrito = carritoRepository.findById(carritoId)
                    .orElseThrow(() -> crearExcepcion(1002,"Carrito no encontrado", "Error Carrito", "http://localhost:8080/api/carrito"));
            Producto producto = productoRepository.findById(productoId)
                    .orElseThrow(() -> crearExcepcion(2000,"Producto no encontrado", "Error Producto", "http://localhost:8080/api/carrito"));

            CarritoProducto carritoProducto = carritoProductoRepository
                    .findByCarritoIdAndProductoId(carritoId, productoId)
                    .orElseThrow(() -> crearExcepcion(2001,"Producto no encontrado en el carrito", "Error Producto", "http://localhost:8080/api/carrito"));

            carritoProducto.setCantidad(cantidad);
            carritoProductoRepository.save(carritoProducto);
        }
        return "Se actualizo la cantidad de forma exitosa";
    }


    public CarritoDTO verCarritoConDescuento(Long carritoId, String codigoCupon) throws DatosException {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> crearExcepcion(1002,"Carrito no encontrado", "Error Carrito", "http://localhost:8080/api/carrito"));

        List<ProductoDTO> productosDTO = carrito.getProductos().stream()
                .map(cp -> {
                    ProductoDTO dto = new ProductoDTO();
                    dto.setNombre(cp.getProducto().getNombre());
                    dto.setCantidad(cp.getCantidad());
                    dto.setPrecioUnitario(cp.getProducto().getPrecio());
                    dto.setSubtotal(cp.getProducto().getPrecio().multiply(BigDecimal.valueOf(cp.getCantidad())));
                    return dto;
                })
                .collect(Collectors.toList());


        BigDecimal total = productosDTO.stream()
                .map(ProductoDTO::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalDescuento = BigDecimal.ZERO;
        if (codigoCupon != null && !codigoCupon.isEmpty()) {
            totalDescuento = aplicarCupon(carrito, codigoCupon);
        }

        CarritoDTO carritoDTO = new CarritoDTO();
        carritoDTO.setProductos(productosDTO);
        carritoDTO.setDescuento(totalDescuento);
        carritoDTO.setTotal(total);
        carritoDTO.setTotalConDescuento(total.subtract(totalDescuento));
        return carritoDTO;
    }


    public BigDecimal aplicarCupon(Carrito carrito, String codigoCupon) throws DatosException{

        Cupon cupon = cuponRepository.findByCodigo(codigoCupon)
                .orElseThrow(() -> crearExcepcion(1001,"El cupón no existe", "Error Cupon", "http://localhost:8080/api/carrito"));

        LocalDate hoy = LocalDate.now();
        if (hoy.isBefore(cupon.getFechaVigenciaInicio()) || hoy.isAfter(cupon.getFechaVigenciaFin()) || cupon.isUtilizado()) {
            throw crearExcepcion(1000,"El cupón no está vigente o ya fue utilizado", "Error Cupon", "http://localhost:8080/api/carrito");
        }

        cupon.setUtilizado(true);
        cuponRepository.save(cupon);

        return cupon.getDescuento();
    }

    private DatosException crearExcepcion(int codigo, String descripcion, String categoria, String path){
        return  DatosException.builder().exceptionResponseObj(ExceptionResponseDto.builder()
                .codigo(codigo)
                .descripcion(descripcion)
                .categoria(categoria)
                .path(path).build()).build();

    }
}
