package com.veterinaria.backend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String sexo;
    private float edad;
    private Boolean castrado;
    private String alergias;

    @ManyToOne
    @JoinColumn(name = "dueno_id")
    private Dueno dueno;


    public Paciente(String nombre, String sexo, float edad, Boolean castrado) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.edad = edad;
        this.castrado = castrado;
    }
    public Paciente(String nombre, String sexo, float edad, Boolean castrado, Dueno dueno) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.edad = edad;
        this.castrado = castrado;
        this.dueno = dueno;
    }
}
