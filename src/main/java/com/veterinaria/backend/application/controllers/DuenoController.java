package com.veterinaria.backend.application.controllers;

import com.veterinaria.backend.application.dto.ResponseHandler;
import com.veterinaria.backend.application.dto.request.CreateDuenoReq;
import com.veterinaria.backend.application.dto.response.DuenoResponse;
import com.veterinaria.backend.services.interfaces.DuenoService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/duenos")
@RequiredArgsConstructor
public class DuenoController {

    private final DuenoService duenoService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            var duenosEntidades = duenoService.findAll();

            // 2. Convertimos cada entidad Dueno a DuenoResponse usando el builder (.from)
            List<DuenoResponse> duenosResponse = duenosEntidades.stream()
                    .map(DuenoResponse::from)
                    .toList();

            return ResponseEntity.ok(duenosResponse);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener la lista de dueños: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody CreateDuenoReq createDuenoReq){

        duenoService.create(createDuenoReq.getNombre(),
                createDuenoReq.getZona(),
                createDuenoReq.getTelefono());
        return ResponseHandler.success("Dueño registrado exitosamente.");

    }

}
