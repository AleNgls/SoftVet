package com.veterinaria.backend.repositories;

import com.veterinaria.backend.domain.Dueno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DuenoRep extends JpaRepository<Dueno, Long> {

    @Override
    Optional<Dueno> findById(Long aLong);

}
