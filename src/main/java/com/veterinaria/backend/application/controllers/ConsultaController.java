package com.veterinaria.backend.application.controllers;

import com.veterinaria.backend.application.dto.ResponseHandler;
import com.veterinaria.backend.application.dto.request.CreateConsultaReq;
import com.veterinaria.backend.application.dto.response.ConsultaResponse;
import com.veterinaria.backend.domain.Consulta;
import com.veterinaria.backend.services.interfaces.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<Object> findAll() {
        var consultas = consultaService.findAll()
                .stream().map(ConsultaResponse::from)
                .toList();
        return ResponseHandler.success(consultas);
    }

    @PostMapping("/paciente/{pacienteId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Object> create(@RequestBody CreateConsultaReq consultaReq, @PathVariable Long pacienteId) {
        // 1. Convertimos los datos del DTO (request) a la entidad de dominio Consulta
        Consulta consulta = new Consulta();
        consulta.setMotivo(consultaReq.getMotivo());
        consulta.setAnamnesis(consultaReq.getAnamnesis());
        consulta.setExamenClinico(consultaReq.getExamenClinico());
        consulta.setDiagnostico(consultaReq.getDiagnostico());
        consulta.setPedidoComplementario(consultaReq.getPedidoComplementario());
        consulta.setTratamiento(consultaReq.getTratamiento());
        consulta.setIndicaciones(consultaReq.getIndicaciones());

        // 2. Pasamos la entidad armada y el pacienteId al servicio
        consultaService.create(consulta, pacienteId);

        // 3. Retornamos la respuesta de éxito
        return ResponseHandler.success("Consulta creada exitosamente");
    }
}