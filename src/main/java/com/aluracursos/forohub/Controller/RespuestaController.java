package com.aluracursos.forohub.Controller;

import com.aluracursos.forohub.domain.respuesta.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respuesta")
@ResponseBody
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {
    @Autowired
    private RespuestaService respuestaService;

    @GetMapping
    public ResponseEntity<Page<RespuestaDTO>> listarRespuestas(@PageableDefault(size = 5) Pageable pageable){
        return ResponseEntity.ok(respuestaService.listarRespuestas(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaDTO> obtenerRespuestra(@PathVariable Long id){
        RespuestaDTO respuestaDTO = respuestaService.obtenerRespuesta(id);
        return ResponseEntity.ok(respuestaDTO);
    }

    @PostMapping
    public ResponseEntity<RespuestaDTO> publicarRespuesta(@RequestBody @Valid DatosRespuesta datos,
                                                          UriComponentsBuilder uriBuilder){
        Respuesta respuesta = respuestaService.registrarRespuesta(datos);
        RespuestaDTO respuestaDTO = new RespuestaDTO(respuesta);
        URI uri = uriBuilder.path("respuesta/{id}").buildAndExpand(respuesta.getId()).toUri();
        return ResponseEntity.created(uri).body(respuestaDTO);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RespuestaDTO> editarRespuesta(@RequestBody @Valid DatosActualizarRespuesta datos){
        var nuevaRespuesta = respuestaService.editarDatos(datos);
        return ResponseEntity.ok(nuevaRespuesta);
    }
}
