package com.veterinaria.backend.services.interfaces;

import com.veterinaria.backend.domain.Paciente;

import java.util.Optional;

public interface PacienteService {

    Optional<Paciente> findById(long id);
    void create(String nombre, String sexo, float edad, Boolean castrado);
    void update(String nombre, String sexo, float edad);


}
