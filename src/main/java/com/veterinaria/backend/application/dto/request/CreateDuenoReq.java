package com.veterinaria.backend.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateDuenoReq {

    @NotBlank(message = "Nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message = "Zona no puede estar vacio")
    private String zona;

    @NotBlank(message = "Telefono no puede estar vacio")
    private String telefono;

}
