package com.aluracursos.forohub.domain.topico;

import com.aluracursos.forohub.domain.curso.Curso;
import com.aluracursos.forohub.domain.curso.CursoRepository;
import com.aluracursos.forohub.domain.usuario.Usuario;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import com.aluracursos.forohub.infra.errores.ErrorDeValidacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {
    @Autowired
    private UsuarioRepository usuarioRepo;
    @Autowired
    private CursoRepository cursoRepo;
    @Autowired
    private TopicoRepository topicoRepo;

    public Page<RespuestaTopico> listarTopicos(Pageable pageable) {
        return topicoRepo.findByStutusNotInactivo(pageable).map(RespuestaTopico::new);
    }

    public Topico obtenerTopico(Long id) {
        return validarExistenciaTopico(id);
    }

    public Topico registrarTopico(DatosTopico datos){
        validarIdUsuarioCurso(datos);
        Usuario usuario = usuarioRepo.getReferenceById(datos.autorId());
        Curso curso = cursoRepo.getReferenceById(datos.cursoId());
        var topico = new Topico(datos.titulo(), datos.mensaje(), usuario, curso);
        topicoRepo.save(topico);
        return topico;
    }

    public RespuestaTopico actualizarTopico(Long id, DatosActualizarTopico datos) {
        var topico = validarExistenciaTopico(id);
        topico.actualizarDatos(datos);
        return new RespuestaTopico(topico);
    }

    public void desactivarTopico(Long id) {
        var topico = validarExistenciaTopico(id);
        topico.desactivarTopico();
    }

    public RespuestaTopico topicoResuelto(Long id) {
        var topico = validarExistenciaTopico(id);
        topico.statusResuelto();
        return new RespuestaTopico(topico);
    }

    public void validarIdUsuarioCurso(DatosTopico datos){
        if (!usuarioRepo.existsById(datos.autorId())){
            throw new ErrorDeValidacion("El usuario ingresado no esta registrado");
        }
        if (!cursoRepo.existsById(datos.cursoId())){
            throw new ErrorDeValidacion("El curso ingresado no existe");
        }
    }

    public Topico validarExistenciaTopico(Long id){
        if (!topicoRepo.existsById(id)){
            throw new ErrorDeValidacion("El id del topico no existe");
        }
        return topicoRepo.getReferenceById(id);
    }
}
