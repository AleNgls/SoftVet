package com.veterinaria.backend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleDeVenta> detalles = new ArrayList<>();

    private float total;

    private float cantProductos;


    @PrePersist
    @PreUpdate
    public void calcularTotales() {
        this.total = 0;
        this.cantProductos = 0;

        if (detalles != null && !detalles.isEmpty()) {
            for (DetalleDeVenta detalle : detalles) {
                this.cantProductos += detalle.getCantidad();
                // Usa el getter que calcula la multiplicación directamente
                this.total += detalle.getSubtotal();
            }
        }
    }

    // Método de utilidad para mantener sincronizada la bidireccionalidad
    public void addDetalle(DetalleDeVenta detalle) {
        detalles.add(detalle);
        detalle.setVenta(this);
        calcularTotales(); // Opcional: recalcular al vuelo al agregar
    }
}

