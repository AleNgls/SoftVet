package com.veterinaria.backend.application.dto.response;

import com.veterinaria.backend.domain.Dueno;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DuenoResponse {

    private Long id;
    private String nombre;
    private String zona;
    private String telefono;
    private List<PacienteInfo> mascotas;

    // Método estático From para mapear desde la entidad Dueno
    public static DuenoResponse from(Dueno dueno) {
        return DuenoResponse.builder()
                .id(dueno.getId())
                .nombre(dueno.getNombre())
                .zona(dueno.getZona())
                .telefono(dueno.getTelefono())

                .mascotas(dueno.getMascotas().stream()
                        .map(paciente -> PacienteInfo.builder()
                                .id(paciente.getId())
                                .nombre(paciente.getNombre())
                                .sexo(paciente.getSexo())
                                .edad(paciente.getEdad())
                                .castrado(paciente.getCastrado())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PacienteInfo {
        private Long id;
        private String nombre;
        private String sexo;
        private Float edad;
        private Boolean castrado;
    }
}