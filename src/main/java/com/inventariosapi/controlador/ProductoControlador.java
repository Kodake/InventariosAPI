package com.inventariosapi.controlador;

import com.inventariosapi.dto.ProductoDTO;
import com.inventariosapi.excepcion.RecursoNoEncontradoExcepcion;
import com.inventariosapi.modelo.Producto;
import com.inventariosapi.servicio.IProductoServicio;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/inventarios")
@CrossOrigin(value = "http://localhost:4200")
public class ProductoControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(ProductoControlador.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IProductoServicio productoServicio;

    @GetMapping("/productos")
    public List<ProductoDTO> listar() {
        List<Producto> productos = productoServicio.listar();

        List<ProductoDTO> productosDTO = productos.stream()
                .map(producto -> modelMapper.map(producto, ProductoDTO.class))
                .collect(Collectors.toList());

        return productosDTO;
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<ProductoDTO> buscarPorId(@PathVariable Integer id) {
        Producto producto = productoServicio.buscarPorId(id);
        if (producto == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontró el producto con el id: " + id);
        }

        ProductoDTO productoDTO = modelMapper.map(producto, ProductoDTO.class);

        return ResponseEntity.ok(productoDTO);
    }

    @PostMapping("/productos")
    public ResponseEntity<ProductoDTO> agregar(@RequestBody ProductoDTO productoDTO) {

        Producto productoNuevo = modelMapper.map(productoDTO, Producto.class);

        Producto producto = productoServicio.guardar(productoNuevo);

        ProductoDTO respuesta = modelMapper.map(producto, ProductoDTO.class);

        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<ProductoDTO> actualizar(@PathVariable Integer id, @RequestBody ProductoDTO productoActualizadoDTO) {
        Producto productoExistente = productoServicio.buscarPorId(id);
        if (productoExistente == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontró el producto con el id: " + id);
        }

        productoActualizadoDTO.setIdProducto(productoExistente.getIdProducto());

        modelMapper.map(productoActualizadoDTO, productoExistente);

        productoServicio.guardar(productoExistente);

        ProductoDTO respuesta = modelMapper.map(productoExistente, ProductoDTO.class);

        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminar(@PathVariable Integer id) {
        Producto producto = productoServicio.buscarPorId(id);
        if (producto == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontró el producto con el id: " + id);
        }
        productoServicio.eliminar(producto.getIdProducto());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
