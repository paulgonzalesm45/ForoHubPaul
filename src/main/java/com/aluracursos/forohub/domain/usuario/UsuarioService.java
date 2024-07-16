package com.aluracursos.forohub.domain.usuario;

import com.aluracursos.forohub.infra.errores.ErrorDeValidacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Page<RespuestaUsuario> listarUsuarios(Pageable pageable) {
        return usuarioRepo.findAll(pageable).map(RespuestaUsuario::new);
    }

    public RespuestaUsuario obtenerUsuario(Long id) {
        if (!usuarioRepo.existsById(id)){
            throw new ErrorDeValidacion("El id del usuario no esta registrado");
        }
        var usuario = usuarioRepo.getReferenceById(id);
        return new RespuestaUsuario(usuario);
    }

    public Usuario registrarUsuario(DatosUsuario datos){
        if (usuarioRepo.existsByNombre(datos.nombre())){
            throw new ErrorDeValidacion("El nombre ya esta en uso");
        }
        if (usuarioRepo.existsByCorreo(datos.correo())){
            throw new ErrorDeValidacion("El correo ya fue registrado");
        }

        var usuario = new Usuario(datos, bCryptPasswordEncoder); // PASAR BYCRYPT Y CODIFICAR LA CONTRA EN EL CONSTRUCTOR
        usuarioRepo.save(usuario);
        return usuario;
    }

    public RespuestaUsuario actualizarUsuario(DatosActualizarUsuario datosActualizar){
        Usuario usuario = usuarioRepo.getReferenceById(datosActualizar.id());
        Usuario usuarioActualizado =  usuario.actualizarDatos(datosActualizar, bCryptPasswordEncoder);
        return new RespuestaUsuario(usuarioActualizado);
    }

    public void deshabilitarUsuario(Long id){
        Usuario usuario = usuarioRepo.getReferenceById(id);
        usuario.deshabilitarUsuario();
    }
}
