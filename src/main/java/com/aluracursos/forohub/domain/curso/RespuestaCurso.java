package com.aluracursos.forohub.domain.curso;

import java.time.LocalDateTime;

public record RespuestaCurso(
        Long id,
        String nombre,
        CategoriaCurso categoria)
{
    public RespuestaCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria());
    }
}
