package com.veterinaria.backend.domain;

import jakarta.persistence.*;

@Entity
public class Dueno {

    @Id
    private long id;
    private String nombre;
    private String zona;
    private String numero;
}
