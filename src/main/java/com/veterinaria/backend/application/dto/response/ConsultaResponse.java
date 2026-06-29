package com.veterinaria.backend.application.dto.response;

import com.veterinaria.backend.domain.Consulta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaResponse {

    private Long id;
    private Long pacienteId;
    private String pacienteNombre;
    private String fechaConsulta;
    private String motivo;
    private String anamnesis;
    private String examenClinico;
    private String diagnostico;
    private String pedidoComplementario;
    private String tratamiento;
    private String indicaciones;

    public static ConsultaResponse from(Consulta consulta){

        return ConsultaResponse.builder()
                .id(consulta.getId())
                .pacienteId(consulta.getPaciente().getId())
                .pacienteNombre(consulta.getPaciente().getNombre())
                .fechaConsulta(consulta.getFechaConsulta().toString())
                .motivo(consulta.getMotivo())
                .anamnesis(consulta.getAnamnesis())
                .examenClinico(consulta.getExamenClinico())
                .diagnostico(consulta.getDiagnostico())
                .pedidoComplementario(consulta.getPedidoComplementario())
                .tratamiento(consulta.getTratamiento())
                .indicaciones(consulta.getIndicaciones())
                .build();
    }

}
