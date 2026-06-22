package com.veterinaria.backend.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePacienteReq {

    @NotBlank(message = "Nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message = "Sexo no puede estar vacio")
    private String sexo;

    @NotNull(message = "Edad no puede estar vacio")
    private float edad;

    private Boolean castrado;
}
