package com.veterinaria.backend.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateProductoReq {

    @NotBlank(message = "Debe ingresar el nombre del producto.")
    private String nombre;
    private String marca;
    private float precio;
    private int stock;

}
