package com.aluracursos.forohub.domain.usuario;

import com.aluracursos.forohub.domain.topico.RespuestaTopico;
import com.aluracursos.forohub.domain.topico.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByNombre(String nombre);

    boolean existsByCorreo(String correo);

    Usuario findByNombre(String subject);
}
