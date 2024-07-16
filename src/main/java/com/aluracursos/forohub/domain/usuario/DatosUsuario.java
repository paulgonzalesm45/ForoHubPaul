package com.aluracursos.forohub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DatosUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        @Email
        String correo,
        @NotBlank
        String contrasena) {
}
