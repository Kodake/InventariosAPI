package com.inventariosapi.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Existencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idExistencia;
    Double existencia;
}