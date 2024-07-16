package com.aluracursos.forohub.Controller;

import com.aluracursos.forohub.domain.usuario.*;
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
@RequestMapping("/usuarios")
@ResponseBody
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<RespuestaUsuario>> listarUsuarios(@PageableDefault(size = 5) Pageable pageable){
        return ResponseEntity.ok(usuarioService.listarUsuarios(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaUsuario> obtenerUsuario(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.obtenerUsuario(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<RespuestaUsuario> registrarUsuario(@RequestBody @Valid DatosUsuario datos,
                                                             UriComponentsBuilder uriBuilder){
        var usuario = usuarioService.registrarUsuario(datos);
        var respuestaUsuario = new RespuestaUsuario(usuario);
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(respuestaUsuario);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RespuestaUsuario> actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datos){
        var respuestaUsuario = usuarioService.actualizarUsuario(datos);
        return ResponseEntity.ok(respuestaUsuario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> eliminarUsuario(@PathVariable Long id){
        usuarioService.deshabilitarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
