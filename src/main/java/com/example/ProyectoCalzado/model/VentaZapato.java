package com.example.ProyectoCalzado.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "VENTA_ZAPATOS")
@Data
@NoArgsConstructor
public class VentaZapato {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venta_seq")
    @SequenceGenerator(name = "venta_seq", sequenceName = "Venta_zapatos_seq", allocationSize = 1)
    @Column(name = "ID_VENTA")
    private Long idVenta;

    @ManyToOne
    @JoinColumn(name = "SKU", referencedColumnName = "SKU")
    private Zapato zapato;

    @Column(name="CANTIDAD")
    private Integer cantidad;

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA")
    private Date fecha;

    @PrePersist
    protected void onCreate() {
        this.fecha = new Date();
    }

    public VentaZapato(Zapato zapato, Integer cantidad){
        this.zapato=zapato;
        this.cantidad=cantidad;
    }
}
