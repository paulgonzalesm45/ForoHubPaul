package com.aluracursos.forohub.domain.topico;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosTopico(
        @NotBlank
        String titulo,

        @NotBlank
        String mensaje,

        @JsonAlias("id_autor")
        @NotNull
        long autorId,

        @JsonAlias("id_curso")
        @NotNull
        long cursoId) {
}
