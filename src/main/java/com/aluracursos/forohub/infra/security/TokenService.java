package com.aluracursos.forohub.infra.security;

import com.aluracursos.forohub.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
//    @Value("${api.security.secret}")
    private String apiSecret = "ContraseÑaSegurA";

    public String generarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

            return JWT.create().withIssuer("forohub")
                    .withSubject(usuario.getNombre())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);
        } catch (JWTVerificationException e){
            throw new RuntimeException();
        }
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);

            verifier = JWT.require(algorithm)
                    .withIssuer("forohub")
                    .build().verify(token);
        } catch (JWTVerificationException e){
            System.out.println(e.toString());
        }

        if (verifier.getSubject() == null){
            throw new RuntimeException("Verifier invalido");
        }
        return verifier.getSubject();
    }

    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
