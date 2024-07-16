package com.aluracursos.forohub.domain.respuesta;

import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record RespuestaDTO (
        Long id,
        String topico,
        String mensaje,
        LocalDateTime fechaCreacion,
        String autor,
        String solucion) {
    public RespuestaDTO(Respuesta respuesta) {
        this(respuesta.getId(),
                respuesta.getTopico().getTitulo(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getAutor().getNombre(),
                respuesta.getSolucion());
    }
}
