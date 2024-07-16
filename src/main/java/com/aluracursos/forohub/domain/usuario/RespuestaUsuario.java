package com.aluracursos.forohub.domain.usuario;

public record RespuestaUsuario(Long id, String nombre) {
    public RespuestaUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre());
    }
}
