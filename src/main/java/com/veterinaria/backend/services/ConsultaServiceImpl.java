package com.veterinaria.backend.services;

import com.veterinaria.backend.domain.Consulta;
import com.veterinaria.backend.domain.Paciente;
import com.veterinaria.backend.repositories.ConsultaRep;
import com.veterinaria.backend.repositories.PacienteRep;
import com.veterinaria.backend.services.interfaces.ConsultaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRep consultaRep;
    private final PacienteRep pacienteRep;

    @Override
    public List<Consulta> findAll() {
        return consultaRep.findAll();
    }

    @Override
    @Transactional
    public Consulta create(Consulta consulta, Long pacienteId) {
        Paciente paciente = pacienteRep.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con el ID: " + pacienteId));
        consulta.setPaciente(paciente);

        consulta.setFechaConsulta(LocalDateTime.now());

        return consultaRep.save(consulta);
    }
}
