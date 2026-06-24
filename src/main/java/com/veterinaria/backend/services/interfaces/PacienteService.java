package com.veterinaria.backend.services.interfaces;

import com.veterinaria.backend.domain.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {

    Optional<Paciente> findById(long id);

    List<Paciente> findAll();
    void create(String nombre, String sexo, Float edad, Boolean castrado, Long duenoId);
    void update(String nombre, String sexo, float edad);


}
