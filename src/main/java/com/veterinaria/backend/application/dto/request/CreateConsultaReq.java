package com.veterinaria.backend.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateConsultaReq {

    @NotBlank(message = "Motivo no puede estar vacío.")
    private String motivo;
    @NotBlank(message = "Anamnesis no puede estar vacío.")
    private String anamnesis;
    @NotBlank(message = "Examen clinico no puede estar vacío.")
    private String examenClinico;
    @NotBlank(message = "Diagnostico no puede estar vacío.")
    private String diagnostico;
    private String pedidoComplementario;
    private String tratamiento;
    @NotBlank(message = "Indicaciones no puede estar vacío.")
    private String indicaciones;

}