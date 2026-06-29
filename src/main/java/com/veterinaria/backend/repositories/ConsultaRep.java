package com.veterinaria.backend.repositories;

import com.veterinaria.backend.domain.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRep extends JpaRepository<Consulta, Long> {
}
