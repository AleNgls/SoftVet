package com.veterinaria.backend.application.controllers;

import com.veterinaria.backend.application.dto.ResponseHandler;
import com.veterinaria.backend.application.dto.request.CreatePacienteReq;
import com.veterinaria.backend.application.dto.response.PacienteResponse;
import com.veterinaria.backend.domain.Paciente;
import com.veterinaria.backend.services.interfaces.PacienteService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Object> findOne(@PathVariable Long id){

        try {
            return pacienteService.findById(id)
                    .map(paciente -> ResponseHandler.success(PacienteResponse.from(paciente)))
                    .orElseGet(()-> ResponseHandler.notFound("Paciente no encontrado"));
        } catch (IllegalArgumentException e){
                return ResponseHandler.badRequest(e.getMessage());
        }

    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<Object> findAll(){
        try {
            val pacientes = pacienteService.findAll()
                    .stream().map(PacienteResponse::from)
                    .toList();

            return ResponseHandler.success(pacientes);
        } catch (IllegalArgumentException e){

            return ResponseHandler.badRequest(e.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<Object> create(@RequestBody CreatePacienteReq createPacienteReq){

        try {
            pacienteService.create(createPacienteReq.getNombre(),
                    createPacienteReq.getSexo(),
                    createPacienteReq.getEdad(),
                    createPacienteReq.getCastrado(),
                    createPacienteReq.getDuenoId());
            return ResponseHandler.success("Paciente registrado con exito");
        } catch (IllegalArgumentException e){

            return ResponseHandler.badRequest(e.getMessage());
        }

    }
}
