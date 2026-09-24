package com.veterinaria.backend.services;

import com.veterinaria.backend.domain.Producto;
import com.veterinaria.backend.repositories.ProductoRep;
import com.veterinaria.backend.services.interfaces.ProductoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRep productoRep;

    @Override
    public void create(String nombre, String marca, float precio, int stock) {
        var producto = new Producto(nombre, marca, precio, stock);

        productoRep.save(producto);
    }

    @Override
    public List<Producto> findAll() {
        return productoRep.findAll();
    }

    @Override
    public Optional<Producto> findByNombre(String nombre) {
        return productoRep.findByNombre(nombre);
    }

    @Override
    public Optional<Producto> findByMarca(String marca) {
        return productoRep.findByMarca(marca);
    }

    @Override
    public void delete(long id) {
        productoRep.deleteById(id);
    }
}
