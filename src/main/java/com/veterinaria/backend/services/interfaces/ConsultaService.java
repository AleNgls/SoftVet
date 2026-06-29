package com.veterinaria.backend.services.interfaces;

import com.veterinaria.backend.domain.Consulta;

import java.util.List;

public interface ConsultaService {

    List<Consulta> findAll();

    Consulta create(Consulta consulta, Long pacienteId);

}
