package com.aluracursos.forohub.Controller;

import com.aluracursos.forohub.domain.topico.*;
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
@RequestMapping("/topicos")
@ResponseBody
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    @Autowired
    private TopicoService topicoService;

    @GetMapping
    public ResponseEntity<Page<RespuestaTopico>> lisarTopicos(@PageableDefault(size = 5) Pageable pageable){
        return ResponseEntity.ok(topicoService.listarTopicos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaTopico> obtenerTopico(@PathVariable Long id){
        Topico topico = topicoService.obtenerTopico(id);
        var respuesta = new RespuestaTopico(topico);
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping
    public ResponseEntity<RespuestaTopico> registrarTopico(@RequestBody @Valid DatosTopico datosTopico,
                                                           UriComponentsBuilder uriBuilder){
        Topico topico = topicoService.registrarTopico(datosTopico);
        var respuesta = new RespuestaTopico(topico);
        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(respuesta);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<RespuestaTopico> actualizarTopico(@PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico datos){
        var respuesta = topicoService.actualizarTopico(id, datos);
        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{id}/resuelto")
    @Transactional
    public ResponseEntity<RespuestaTopico> topicoResuelto(@PathVariable Long id){
        var respuesta = topicoService.topicoResuelto(id);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> eliminacionTopico(@PathVariable Long id){
        topicoService.desactivarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
