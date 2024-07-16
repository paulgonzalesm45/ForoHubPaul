package com.aluracursos.forohub.domain.topico;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico (
        @NotNull
        Long id,

        String titulo,
        String mensaje)
{
}
