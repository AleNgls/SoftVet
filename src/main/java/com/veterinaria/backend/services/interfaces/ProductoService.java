package com.veterinaria.backend.services.interfaces;


import com.veterinaria.backend.domain.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    void create(String nombre, String marca, float precio, int stock);

    List<Producto> findAll();

    Optional<Producto> findByNombre(String nombre);

    Optional<Producto> findByMarca(String marca);

}
