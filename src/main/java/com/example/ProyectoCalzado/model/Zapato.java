package com.example.ProyectoCalzado.model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name="ZAPATOS")
@Data
public class Zapato {
    @Id
    private String sku;
    private String marca;
    private String modelo;
    private String talla;
    private BigDecimal precio;
    @Column(name = "CANTIDAD_DISPONIBLE")
    private Integer cantidad = 0;
}
