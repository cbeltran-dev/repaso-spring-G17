package com.codigo.cursos.mapper;

import com.codigo.cursos.dto.response.CursoResponse;
import com.codigo.cursos.entity.CursoEntity;
import com.codigo.cursos.entity.EstudianteEntity;

import java.util.List;

public class CursoMapper {

    public static CursoResponse toResponse(CursoEntity curso) {

        CursoResponse response = new CursoResponse();

        response.setIdCurso(curso.getIdCurso());
        response.setNombre(curso.getNombre());
        response.setDescripcion(curso.getDescripcion());
        response.setDuracionHoras(curso.getDuracionHoras());

        String nombre = curso.getDocente().getNombre(); //cesar
        String apellido = curso.getDocente().getApellido();//beltran
        String nombreCompleto = nombre + " " + apellido; //cesar beltran

        response.setNombreDocente(nombreCompleto);


        List<String> nombresEstudiantes = new java.util.ArrayList<>();
        for (EstudianteEntity estudiante : curso.getEstudiantes()) {
            String nombreEst = estudiante.getNombre()
                    + " " + estudiante.getApellido();//Cesar beltran
            nombresEstudiantes.add(nombreEst);
        }

        response.setEstudiantes(nombresEstudiantes);

        return response;
    }
}
