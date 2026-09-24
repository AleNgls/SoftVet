package com.veterinaria.backend.application.dto.response;

import com.veterinaria.backend.domain.Venta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VentaResponse {

    private long id;
    private float cantProductos;
    private float total;
    private List<DetalleVentaResponse> detalles;

    public static VentaResponse from(Venta venta) {
        List<DetalleVentaResponse> detallesResponse = (venta.getDetalles() != null)
                ? venta.getDetalles().stream()
                .map(DetalleVentaResponse::from)
                .toList()
                : Collections.emptyList();

        return VentaResponse.builder()
                .id(venta.getId())
                .cantProductos(venta.getCantProductos())
                .total(venta.getTotal())
                .detalles(detallesResponse)
                .build();
    }
}