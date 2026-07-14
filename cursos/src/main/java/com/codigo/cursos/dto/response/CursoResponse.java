package com.codigo.cursos.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CursoResponse {

    private Long idCurso;
    private String nombre;
    private String descripcion;
    private Integer duracionHoras;
    private String nombreDocente;
    private List<String> estudiantes;

}
