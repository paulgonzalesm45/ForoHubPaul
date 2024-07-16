package com.aluracursos.forohub.domain.respuesta;

import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {
}
