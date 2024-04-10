package com.inventariosapi.controlador;

import com.inventariosapi.excepcion.RecursoNoEncontradoExcepcion;
import com.inventariosapi.modelo.Producto;
import com.inventariosapi.servicio.IProductoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/inventarios")
@CrossOrigin(value = "http://localhost:4200")
public class ProductoControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(ProductoControlador.class);

    @Autowired
    private IProductoServicio productoServicio;

    @GetMapping("/productos")
    public List<Producto> listar() {
        return productoServicio.listar();
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Integer id) {
        Producto producto = productoServicio.buscarPorId(id);
        if (producto == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontró el empleado con el id: " + id);
        }
        return ResponseEntity.ok(producto);
    }

    @PostMapping("/productos")
    public ResponseEntity<Producto> agregar(@RequestBody Producto producto) {
        Producto productoNuevo = productoServicio.guardar(producto);
        return ResponseEntity.ok(productoNuevo);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Integer id, @RequestBody Producto productoActualizado) {
        Producto productoExistente = productoServicio.buscarPorId(id);
        if (productoExistente == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontró el producto con el id: " + id);
        }
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setExistencia(productoActualizado.getExistencia());

        productoServicio.guardar(productoExistente);
        return ResponseEntity.ok(productoExistente);
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
