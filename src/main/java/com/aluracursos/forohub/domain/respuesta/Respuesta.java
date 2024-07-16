package com.aluracursos.forohub.domain.respuesta;

import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Respuesta")
@Table(name = "respuestas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico", referencedColumnName = "id")
    private Topico topico;

    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor", referencedColumnName = "id")
    private Usuario autor;

    private String solucion;

    public Respuesta(DatosRespuesta datos, Topico topico, Usuario usuario) {
        this.mensaje = datos.mensaje();
        this.topico = topico;
        this.fechaCreacion = LocalDateTime.now();
        this.autor = usuario;
        this.solucion = datos.solucion();
    }

    public Respuesta actualizarRespuesta(DatosActualizarRespuesta datos) {
        if (datos.titulo() != null){
            this.mensaje = datos.titulo();
        }
        if (datos.solucion() != null){
            this.solucion = datos.solucion();
        }
        return this;
    }
}
