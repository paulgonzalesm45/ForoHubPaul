package com.aluracursos.forohub.Controller;

import com.aluracursos.forohub.domain.usuario.DatosAutenticacionUsuario;
import com.aluracursos.forohub.domain.usuario.Usuario;
import com.aluracursos.forohub.infra.security.DatosJWToken;
import com.aluracursos.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<Object> autorizarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datos){
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                datos.usuario(),
                datos.contrasena());
        var usuarioAutorizado = authenticationManager.authenticate(authToken);
        var JWTToken = tokenService.generarToken((Usuario) usuarioAutorizado.getPrincipal());
        return ResponseEntity.ok(new DatosJWToken(JWTToken));
    }
}
