package com.veterinaria.backend.application.controllers;

import com.veterinaria.backend.application.dto.ResponseHandler;
import com.veterinaria.backend.application.dto.request.CreateProductoReq;
import com.veterinaria.backend.application.dto.response.ProductoResponse;
import com.veterinaria.backend.services.interfaces.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<Object> getProductos(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String marca) {

        try {
            List<ProductoResponse> productos;

            if (nombre != null && !nombre.isBlank()) {
                productos = productoService.findByNombre(nombre).stream()
                        .map(ProductoResponse::from)
                        .toList();
            } else if (marca != null && !marca.isBlank()) {
                productos = productoService.findByMarca(marca).stream()
                        .map(ProductoResponse::from)
                        .toList();
            } else {
                productos = productoService.findAll().stream()
                        .map(ProductoResponse::from)
                        .toList();
            }

            return ResponseHandler.success(productos);
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> create(@RequestBody CreateProductoReq createProductoReq) {
        try {
            productoService.create(
                    createProductoReq.getNombre(),
                    createProductoReq.getMarca(),
                    createProductoReq.getPrecio(),
                    createProductoReq.getStock());

            return ResponseHandler.success("Producto registrado correctamente.");
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        }
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')") // Nota: tenías 'ADMIN' sin comillas simples
    public ResponseEntity<Object> delete(@RequestParam Long id) {
        try {
            productoService.delete(id);
            return ResponseHandler.success("Producto eliminado correctamente.");
        } catch (IllegalArgumentException e) {
            return ResponseHandler.badRequest(e.getMessage());
        }
    }
}