package com.veterinaria.backend.application.controllers;

import com.veterinaria.backend.application.dto.ResponseHandler;
import com.veterinaria.backend.application.dto.request.CreateVentaReq;
import com.veterinaria.backend.application.dto.response.VentaResponse;
import com.veterinaria.backend.domain.Venta;
import com.veterinaria.backend.services.interfaces.VentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final VentaService ventaService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CreateVentaReq ventaRequestDTO) {
        try {
            ventaService.create(ventaRequestDTO);

            return ResponseHandler.success("Venta registrada.");
        } catch (IllegalArgumentException e){

            return ResponseHandler.badRequest(e.getMessage());

        }
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {

        try {
            var ventas = ventaService.findAll()
                    .stream().map(VentaResponse::from)
                    .toList();
            return ResponseHandler.success(ventas);

        } catch (IllegalArgumentException e){

            return ResponseHandler.badRequest(e.getMessage());
        }
    }


}
