package com.inventariosapi.servicio;

import com.inventariosapi.modelo.Producto;
import java.util.List;

public interface IProductoServicio {
    public List<Producto> listar();
    public Producto buscarPorId(Integer idProducto);
    public Producto guardar(Producto producto);
    public void eliminar(Integer idProducto);
}
