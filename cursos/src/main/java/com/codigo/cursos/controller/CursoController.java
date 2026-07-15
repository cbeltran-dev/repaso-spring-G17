package com.codigo.cursos.controller;

import com.codigo.cursos.dto.response.CursoResponse;
import com.codigo.cursos.entity.CursoEntity;
import com.codigo.cursos.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/curso")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService cursoService;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<CursoResponse> findById(@PathVariable Long id){
        CursoResponse cursoResponse = cursoService.findById(id);

        if (cursoResponse == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cursoResponse);
    }
}
