package com.veterinaria.backend.application.controllers;

import com.veterinaria.backend.application.dto.ResponseHandler;
import com.veterinaria.backend.application.dto.request.CreateProductoReq;
import com.veterinaria.backend.application.dto.response.ProductoResponse;
import com.veterinaria.backend.services.interfaces.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;


    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<Object> findAll(){

        var productos = productoService.findAll().stream()
                .map(ProductoResponse::from)
                .toList();
        return ResponseHandler.success(productos);
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> create(@RequestBody CreateProductoReq createProductoReq){

        try {
            productoService.create(createProductoReq.getNombre(),
                    createProductoReq.getMarca(),
                    createProductoReq.getPrecio(),
                    createProductoReq.getStock());

            return ResponseHandler.success("Producto registrado correctamente.");
        } catch (IllegalArgumentException e){

            return ResponseHandler.badRequest(e.getMessage());
        }

    }


}
