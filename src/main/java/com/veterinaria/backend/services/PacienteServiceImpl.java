package com.veterinaria.backend.services;

import com.veterinaria.backend.domain.Dueno;
import com.veterinaria.backend.domain.Paciente;
import com.veterinaria.backend.repositories.DuenoRep;
import com.veterinaria.backend.repositories.PacienteRep;
import com.veterinaria.backend.services.interfaces.PacienteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRep pacienteRep;
    private final DuenoRep duenoRep;

    @Override
    public Optional<Paciente> findById(long id) {
        return pacienteRep.findById(id);
    }

    @Override
    public List<Paciente> findAll() {
        return pacienteRep.findAll();
    }

    @Override
    public void create(String nombre, String sexo, Float edad, Boolean castrado, Long duenoId) {

        Dueno duenoExistente = duenoRep.findById(duenoId)
                .orElseThrow(() -> new RuntimeException("No existe ese dueño."));

        var paciente = new Paciente(nombre, sexo, edad, castrado, duenoExistente);

        pacienteRep.save(paciente);
    }

    @Override
    public void update(String nombre, String sexo, float edad) {

    }
}
