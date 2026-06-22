package com.veterinaria.backend.repositories;

import com.veterinaria.backend.domain.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRep extends JpaRepository<Paciente, Long> {

    @Override
    Optional<Paciente> findById(Long aLong);



}
