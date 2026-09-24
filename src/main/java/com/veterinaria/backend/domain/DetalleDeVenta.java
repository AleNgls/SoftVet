package com.veterinaria.backend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleDeVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productoId;

    private String nombreProducto;

    private float precioUnitario;

    private float cantidad;

    private float subtotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id")
    private Venta venta;

    // Calcula el subtotal en tiempo real al consultarlo
    public float getSubtotal() {
        return this.cantidad * this.precioUnitario;
    }
}