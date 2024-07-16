package com.aluracursos.forohub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacionUsuario(
        @NotBlank
        String usuario,
        @NotBlank
        String contrasena
) {
}
