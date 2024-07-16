package com.aluracursos.forohub.domain.usuario;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;
    private String correo;
    private String contrasena;
    private boolean activo;

    public Usuario(DatosUsuario datos, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.nombre = datos.nombre();
        this.correo = datos.correo();
        this.contrasena = bCryptPasswordEncoder.encode(datos.contrasena());
        this.activo = true;
    }

    public Usuario actualizarDatos(DatosActualizarUsuario datosActualizar,
                                   BCryptPasswordEncoder bCryptPasswordEncoder) {
        if (datosActualizar.nombre() != null){
            this.nombre = datosActualizar.nombre();
        }
        if (datosActualizar.correo() != null){
            this.correo = datosActualizar.correo();
        }
        if (datosActualizar.contrasena() != null){
            this.contrasena = bCryptPasswordEncoder.encode(datosActualizar.contrasena());
        }
        return this;
    }

    public void deshabilitarUsuario() {
        this.activo = false;
    }

    @Override
    public String toString() {
        return "id: " + id +
                ", nombre: " + nombre;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
