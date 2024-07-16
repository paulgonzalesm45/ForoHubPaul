package com.aluracursos.forohub.Controller;

import com.aluracursos.forohub.domain.curso.*;
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
@RequestMapping("/cursos")
@ResponseBody
@SecurityRequirement(name = "bearer-key")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<Page<RespuestaCurso>> listarCursos(@PageableDefault(size = 5) Pageable pageable){
        return ResponseEntity.ok(cursoService.listarCursos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaCurso> obtenerCurso(@PathVariable Long id){
        Curso curso = cursoService.obtenerCurso(id);
        var respuesta = new RespuestaCurso(curso);
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<RespuestaCurso> registrarCurso(@RequestBody @Valid DatosCurso datos,
                                                         UriComponentsBuilder uriBuilder){
        Curso curso = cursoService.registrarCurso(datos);
        var respuesta = new RespuestaCurso(curso);
        URI uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(respuesta);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RespuestaCurso> modificarCurso(@RequestBody @Valid DatosActualizarCurso datos){
        var respuesta = cursoService.actualizarCurso(datos);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> eliminarCurso(@PathVariable Long id){
        cursoService.deshabilitarCurso(id);
        return ResponseEntity.noContent().build();
    }
}
