package com.codigo.cursos.controller;

import com.codigo.cursos.dto.request.DocenteRequestDto;
import com.codigo.cursos.dto.response.DocenteResponseDto;
import com.codigo.cursos.entity.DocenteEntity;
import com.codigo.cursos.service.DocenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("/api/v1/docente")
@RequiredArgsConstructor
public class DocenteController {

    private final DocenteService docenteService;

    @PostMapping("/crear")
    public ResponseEntity<DocenteResponseDto> crear(@Valid @RequestBody DocenteRequestDto request){
        DocenteResponseDto response = docenteService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity <DocenteResponseDto> buscarPorId(@PathVariable Long id){
        DocenteResponseDto responseDto = docenteService.buscarPorId(id);

        if (responseDto == null){
           return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/buscar-especialidad")
    public ResponseEntity<List<DocenteResponseDto>> buscarPorEspecialidad(@RequestParam String especialidad){
        return ResponseEntity.ok(docenteService.buscarPorEspecialidad(especialidad));
    }
}
