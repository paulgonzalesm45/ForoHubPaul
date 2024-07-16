package com.aluracursos.forohub.domain.curso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    @Query("""
            SELECT
            CASE
                WHEN COUNT(c) > 0 THEN TRUE
                ELSE FALSE
            END
            FROM Curso c
            WHERE LOWER(c.nombre) = LOWER(:nombre)
            """)
    boolean existsByNombreIgnoreCase(String nombre);

    Page<Curso> findByActivoTrue(Pageable pageable);
}
