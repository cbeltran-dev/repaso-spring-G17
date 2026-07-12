package com.codigo.cursos.repository;

import com.codigo.cursos.entity.DocenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.print.Doc;
import java.util.List;

public interface DocenteRepository extends JpaRepository<DocenteEntity, Long> {

    List<DocenteEntity> findByEspecialidadIgnoreCase (String especialidad);

    @Query("""
            SELECT d
            FROM DocenteEntity d
            WHERE d.especialidad = :especialidad
            """)
    List<DocenteEntity> findByEspecialidadJPQL(@Param("especialidad") String especialidad);

    @Query(value = """
            SELECT *
            FROM docentes
            WHERE especialidad = :especialidad
            """ , nativeQuery = true)
    List<DocenteEntity> findByEspecialidadQueryNativa(@Param("especialidad") String especialidad);

}
