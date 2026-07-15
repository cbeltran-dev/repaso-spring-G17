package com.codigo.cursos.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cursos")
@Getter
@Setter
public class CursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Long idCurso;
    @Column(nullable = false, length = 255)
    private String nombre;
    @Column(nullable = false, length = 255)
    private String descripcion;
    @Column(nullable = false, name = "duracion_horas")
    private Integer duracionHoras;

    @ManyToOne
    @JoinColumn(name = "docente_id_fk", nullable = false)
    private DocenteEntity docente;

    @ManyToMany
    @JoinTable(
            name = "curso_estudiante",
            joinColumns = @JoinColumn(name = "curso_id_fk"),
            inverseJoinColumns = @JoinColumn(name = "estudiante_id_fk")
    )
    private Set<EstudianteEntity> estudiantes = new HashSet<>();





}
