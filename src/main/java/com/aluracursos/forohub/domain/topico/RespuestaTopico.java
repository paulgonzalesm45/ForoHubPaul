package com.aluracursos.forohub.domain.topico;

import com.aluracursos.forohub.domain.curso.Curso;

import java.time.LocalDateTime;

public record RespuestaTopico(
        long id, String titulo,
        String mensaje,
        String curso,
        String autor,
        LocalDateTime fechaCreacion,
        Status status)
{
    public RespuestaTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getCurso().getNombre(), topico.getUsuario().getNombre(),
                topico.getFechaCreacion(), topico.getStatus());
    }
}
