package com.veterinaria.backend.application.dto.request;

import com.veterinaria.backend.domain.Dueno;
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
    private Float edad;

    private Boolean castrado;

    @NotNull(message = "Debe tener dueño")
    private Long duenoId;

}
