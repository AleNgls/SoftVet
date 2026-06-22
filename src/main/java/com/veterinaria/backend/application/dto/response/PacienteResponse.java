package com.veterinaria.backend.application.dto.response;

import com.veterinaria.backend.domain.Paciente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PacienteResponse {

    long id;
    String nombre;
    String sexo;
    float edad;
    boolean castrado;

    public static PacienteResponse from(Paciente paciente){

        return PacienteResponse.builder()
                .id(paciente.getId())
                .nombre(paciente.getNombre())
                .sexo(paciente.getSexo())
                .edad(paciente.getEdad())
                .castrado(paciente.getCastrado())
                .build();
    }

}
