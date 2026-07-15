package com.codigo.cursos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "docentes")
@Getter
@Setter
@NoArgsConstructor
public class DocenteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_docente")
    private Long idDocente;
    @Column(nullable = false, length = 60)
    private String nombre;
    @Column(nullable = false, length = 120)
    private String apellido;
    @Column(nullable = false, length = 80)
    private String especialidad;
    @Column(nullable = false, length = 120, unique = true)
    private String email;

//    @OneToMany(mappedBy = "docente")
//    private CursoEntity curso;

}
