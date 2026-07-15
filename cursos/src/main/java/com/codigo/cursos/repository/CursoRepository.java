package com.codigo.cursos.repository;

import com.codigo.cursos.entity.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<CursoEntity, Long> {

}
