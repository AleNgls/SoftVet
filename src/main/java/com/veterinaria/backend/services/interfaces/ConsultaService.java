package com.veterinaria.backend.services.interfaces;

import com.veterinaria.backend.domain.Consulta;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ConsultaService {

    List<Consulta> findAll();

    Consulta create(Consulta consulta, Long pacienteId, MultipartFile pdfFile);

}
