package com.inventariosapi.dto;

import lombok.Data;

@Data
public class ProductoDTO {
    Integer idProducto;
    String descripcion;
    Double precio;
    Double existencia;
}
