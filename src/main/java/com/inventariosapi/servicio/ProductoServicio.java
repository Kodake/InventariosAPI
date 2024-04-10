package com.inventariosapi.servicio;

import com.inventariosapi.modelo.Producto;
import com.inventariosapi.repositorio.IProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicio implements IProductoServicio {
    @Autowired
    private IProductoRepositorio productoRepositorio;

    @Override
    public List<Producto> listar() {
        return productoRepositorio.findAll();
    }

    @Override
    public Producto buscarPorId(Integer idProducto) {
        return productoRepositorio.findById(idProducto).orElse(null);
    }

    @Override
    public Producto guardar(Producto producto) {
        return productoRepositorio.save(producto);
    }

    @Override
    public void eliminar(Integer idProducto) {
        productoRepositorio.deleteById(idProducto);
    }
}
