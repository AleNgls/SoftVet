package com.veterinaria.backend.application.dto.response;

import com.veterinaria.backend.domain.Producto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductoResponse {

    long id;
    String nombre;
    String marca;
    float precio;
    int stock;

    public static ProductoResponse from(Producto producto){

        return ProductoResponse.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .marca(producto.getMarca() != null ? producto.getMarca() : "Generico")
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .build();
    }



}
