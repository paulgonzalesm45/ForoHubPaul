package com.aluracursos.forohub.domain.curso;

import com.aluracursos.forohub.infra.errores.ErrorDeValidacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public Page<RespuestaCurso> listarCursos(Pageable pageable) {
        return cursoRepository.findByActivoTrue(pageable).map(RespuestaCurso::new);
    }

    public Curso obtenerCurso(Long id) {
        return verificarExistencia(id);
    }

    public Curso registrarCurso(DatosCurso datos) {
        if (cursoRepository.existsByNombreIgnoreCase(datos.nombre())){
            throw new ErrorDeValidacion("El curso ya fue registrado");
        }
        Curso curso = new Curso(datos);
        cursoRepository.save(curso);
        return curso;
    }

    public void deshabilitarCurso(Long id){
        Curso curso = verificarExistencia(id);
        curso.deactivar();
    }

    public RespuestaCurso actualizarCurso(DatosActualizarCurso datos) {
        Curso curso = verificarExistencia(datos.id());
        curso.actualizarCurso(datos);
        return new RespuestaCurso(curso);
    }

    public Curso verificarExistencia(Long id){
        if (!cursoRepository.existsById(id)){
            throw new ErrorDeValidacion("No existe un curso con el id proporcionado");
        }
        return cursoRepository.getReferenceById(id);
    }
}
