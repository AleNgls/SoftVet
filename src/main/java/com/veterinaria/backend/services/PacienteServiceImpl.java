package com.veterinaria.backend.services;

import com.veterinaria.backend.domain.Paciente;
import com.veterinaria.backend.repositories.PacienteRep;
import com.veterinaria.backend.services.interfaces.PacienteService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    PacienteRep pacienteRep;

    @Override
    public Optional<Paciente> findById(long id) {
        return pacienteRep.findById(id);
    }

    @Override
    public void create(String nombre, String sexo, float edad, Boolean castrado) {
        val paciente = new Paciente(nombre, sexo, edad, castrado);
        pacienteRep.save(paciente);
    }

    @Override
    public void update(String nombre, String sexo, float edad) {

    }
}
