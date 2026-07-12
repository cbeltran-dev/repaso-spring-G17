package com.codigo.cursos.service;

import com.codigo.cursos.dto.request.DocenteRequestDto;
import com.codigo.cursos.dto.response.DocenteResponseDto;
import com.codigo.cursos.entity.DocenteEntity;
import com.codigo.cursos.mapper.DocenteMapper;
import com.codigo.cursos.repository.DocenteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocenteService {

    private final DocenteRepository docenteRepository;
    private final DocenteMapper docenteMapper;

    public DocenteResponseDto crear(DocenteRequestDto request){
        DocenteEntity docenteBD = docenteRepository.save(docenteMapper.toEntity(request));
        return  docenteMapper.toResponse(docenteBD);
    }

    public DocenteResponseDto buscarPorId(Long id){
        Optional<DocenteEntity> optionalDocente = docenteRepository.findById(id);

        DocenteEntity docenteBD = optionalDocente.orElse(null);


        return docenteMapper.toResponse(docenteBD);
    }

    public List<DocenteResponseDto> buscarPorEspecialidad(String especialidad){
        List<DocenteEntity> docenteEntityList = docenteRepository.findByEspecialidadIgnoreCase(especialidad);

        List<DocenteResponseDto> docenteResponseList = new ArrayList<>();

        for (DocenteEntity docente : docenteEntityList){
            docenteResponseList.add(docenteMapper.toResponse(docente));
        }

        return docenteResponseList;
    }

}
