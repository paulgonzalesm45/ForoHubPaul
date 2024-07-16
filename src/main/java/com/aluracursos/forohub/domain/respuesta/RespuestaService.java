package com.aluracursos.forohub.domain.respuesta;

import com.aluracursos.forohub.domain.topico.TopicoRepository;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import com.aluracursos.forohub.infra.errores.ErrorDeValidacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {
    @Autowired
    private RespuestaRepository respuestaRepo;

    @Autowired
    private TopicoRepository topicoRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    public Page<RespuestaDTO> listarRespuestas(Pageable pageable) {
        Page<Respuesta> paginacion = respuestaRepo.findAll(pageable);
        return paginacion.map(RespuestaDTO::new);
    }

    public RespuestaDTO obtenerRespuesta(Long id) {
        validarExistencia(id);
        var respuesta = respuestaRepo.getReferenceById(id);
        return new RespuestaDTO(respuesta);
    }

    public Respuesta registrarRespuesta(DatosRespuesta datos) {
        validarRespuesta(datos);
        var topico = topicoRepo.getReferenceById(datos.topicoId());
        var usuario = usuarioRepo.getReferenceById(datos.autorId());
        var respuesta = new Respuesta(datos, topico, usuario);
        topico.agregarRespuesta(respuesta);
        respuestaRepo.save(respuesta);
        return respuesta;
    }

    public RespuestaDTO editarDatos(DatosActualizarRespuesta datos) {
        validarExistencia(datos.id());
        var respuesta = respuestaRepo.getReferenceById(datos.id());
        var nuevaRespuesta = respuesta.actualizarRespuesta(datos);
        return new RespuestaDTO(nuevaRespuesta);
    }

    private void validarRespuesta(DatosRespuesta datos){
        if (!topicoRepo.existsById(datos.topicoId())){
            throw new ErrorDeValidacion("El id del topico no ha sido registrado");
        }
        if (!usuarioRepo.existsById(datos.autorId())){
            throw new ErrorDeValidacion("El id del usuario no ha sido registrado");
        }
    }

    private void validarExistencia(Long id){
        if (!respuestaRepo.existsById(id)){
            throw new ErrorDeValidacion("No existe el id del usuario");
        }
    }
}
