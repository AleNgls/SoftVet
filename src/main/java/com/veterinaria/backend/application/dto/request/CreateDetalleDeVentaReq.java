package com.veterinaria.backend.application.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateDetalleDeVentaReq {

    @NotNull(message = "El ID del producto es obligatorio")
    private Long productoId;

    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private int cantidad;

}
