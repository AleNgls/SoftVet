package com.veterinaria.backend.services;

import com.veterinaria.backend.domain.Dueno;
import com.veterinaria.backend.repositories.DuenoRep;
import com.veterinaria.backend.services.interfaces.DuenoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DuenoServiceImpl implements DuenoService {

    private final DuenoRep duenoRep;

    @Override
    public Optional<Dueno> findById(Long id) {
        return duenoRep.findById(id);
    }

    @Override
    public List<Dueno> findAll() {
        return duenoRep.findAll();
    }

    @Override
    public void create(String nombre, String zona, String telefono) {

        var dueno = new Dueno(nombre, zona, telefono);

        duenoRep.save(dueno);
    }
}
