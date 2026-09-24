package com.veterinaria.backend.application.dto.response;

import com.veterinaria.backend.domain.DetalleDeVenta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaResponse {

    private String nombreProducto;
    private float cantidad;
    private float precioUnitario;
    private float subtotal;

    public static DetalleVentaResponse from(DetalleDeVenta detalleDeVenta){

        return DetalleVentaResponse.builder()
                .nombreProducto(detalleDeVenta.getNombreProducto())
                .cantidad(detalleDeVenta.getCantidad())
                .precioUnitario(detalleDeVenta.getPrecioUnitario())
                .subtotal(detalleDeVenta.getSubtotal())
                .build();
    }

}
