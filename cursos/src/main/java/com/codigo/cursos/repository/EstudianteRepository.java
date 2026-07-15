package com.codigo.cursos.repository;

import com.codigo.cursos.entity.EstudianteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<EstudianteEntity,Long> {
}
