package com.inventariosapi.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Precio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idPrecio;
    Double precio;
}