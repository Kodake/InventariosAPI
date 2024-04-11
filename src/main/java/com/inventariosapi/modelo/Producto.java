package com.inventariosapi.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idProducto;
    String descripcion;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_precio", referencedColumnName = "idPrecio")
    private Precio precio;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_existencia", referencedColumnName = "idExistencia")
    private Existencia existencia;
}
