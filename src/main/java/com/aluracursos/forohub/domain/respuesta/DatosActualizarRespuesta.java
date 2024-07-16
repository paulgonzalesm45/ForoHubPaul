package com.aluracursos.forohub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarRespuesta(
        @NotNull
        Long id,

        String titulo,
        String solucion) {
}
