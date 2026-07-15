package com.codigo.cursos.service;

import com.codigo.cursos.dto.response.CursoResponse;
import com.codigo.cursos.entity.CursoEntity;
import com.codigo.cursos.mapper.CursoMapper;
import com.codigo.cursos.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CursoService {
    private final CursoRepository cursoRepository;

    public CursoResponse findById(Long id){
        Optional<CursoEntity> cursoOptional = cursoRepository.findById(id);
        if (cursoOptional.isEmpty()){
            return null;
        }

        return CursoMapper.toResponse(cursoOptional.get());
    }

}
