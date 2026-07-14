package com.codigo.cursos.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CursoEntity {

    private Long idCurso;
    private String nombre;
    private String descripcion;
    private Integer duracionHoras;

}
