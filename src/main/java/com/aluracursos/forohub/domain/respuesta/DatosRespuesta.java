package com.aluracursos.forohub.domain.respuesta;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRespuesta(
        @NotBlank
        String mensaje,

        @JsonAlias("topico_id")
        @NotNull
        Long topicoId,

        @JsonAlias("autor_id")
        @NotNull
        Long autorId,

        @NotBlank
        String solucion) {
}
