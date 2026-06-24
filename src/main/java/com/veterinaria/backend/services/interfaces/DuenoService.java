package com.veterinaria.backend.services.interfaces;

import com.veterinaria.backend.domain.Dueno;

import java.util.List;
import java.util.Optional;

public interface DuenoService {

    Optional<Dueno> findById(Long id);

    List<Dueno> findAll();

    void create(String nombre, String zona, String numero);
}
