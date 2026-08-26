package com.veterinaria.backend.repositories;

import com.veterinaria.backend.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRep extends JpaRepository<Producto, Long> {

    Optional<Producto> findByNombre(String nombre);

    Optional<Producto> findByMarca(String marca);

}
