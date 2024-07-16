package com.aluracursos.forohub.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(
        @NotNull(message = "Debe especificar el id del usuario")
        Long id,
        String nombre,
        @Email(message = "Debe ingresar una direccion de correo valida")
        String correo,
        String contrasena) {
}
