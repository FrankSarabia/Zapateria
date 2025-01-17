package com.example.ProyectoCalzado.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="RECIBO_ZAPATOS")
@Data
@NoArgsConstructor
public class EntregaZapato {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recibo_seq")
    @SequenceGenerator(name = "recibo_seq", sequenceName = "Recibo_zapatos_seq", allocationSize = 1)
    @Column(name = "ID_RECIBO")
    private Long idRecibo;

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
}
