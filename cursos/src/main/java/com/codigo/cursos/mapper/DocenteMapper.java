package com.codigo.cursos.mapper;

import com.codigo.cursos.dto.request.DocenteRequestDto;
import com.codigo.cursos.dto.response.DocenteResponseDto;
import com.codigo.cursos.entity.DocenteEntity;
import org.springframework.stereotype.Component;

@Component
public class DocenteMapper {

    public DocenteEntity toEntity (DocenteRequestDto request){
        DocenteEntity docente = new DocenteEntity();
        docente.setNombre(request.getNombre());
        docente.setApellido(request.getApellido());
        docente.setEspecialidad(request.getEspecialidad());
        docente.setEmail(request.getEmail());

        return docente;
    }

    public DocenteResponseDto toResponse(DocenteEntity docente){
        DocenteResponseDto response = new DocenteResponseDto();

        response.setNombre(docente.getNombre());
        response.setApellido(docente.getApellido());
        response.setEspecialidad(docente.getEspecialidad());
        response.setEmail(docente.getEmail());

        return response;
    }
}
