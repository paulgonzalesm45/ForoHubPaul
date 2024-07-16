package com.aluracursos.forohub.domain.topico;

import com.aluracursos.forohub.domain.curso.Curso;
import com.aluracursos.forohub.domain.respuesta.Respuesta;
import com.aluracursos.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private Status status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso", referencedColumnName = "id")
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Respuesta> respuestas;

    public Topico(String titulo, String mensaje, Usuario usuario, Curso curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = LocalDateTime.now();
        this.status = Status.ACTIVO;
        this.usuario = usuario;
        this.curso = curso;
    }

    public void actualizarStatus(Status status){
        this.status = status;
    }

    public void actualizarDatos(DatosActualizarTopico datos) {
        if (datos.titulo()!=null){
            this.titulo = datos.titulo();
        }
        if (datos.mensaje()!=null){
            this.mensaje = datos.mensaje();
        }
    }

    public void desactivarTopico() {
        this.status = Status.INACTIVO;
    }

    public void statusResuelto() {
        this.status = Status.RESUELTO;
    }

    public void agregarRespuesta(Respuesta respuesta) {
        this.respuestas.add(respuesta);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", titulo: " + titulo +
                ", mensaje: " + mensaje +
                ", fechaCreacion: " + fechaCreacion +
                ", status: " + status +
                ", usuario: " + usuario.getNombre() +
                ", curso: " + curso.getNombre() +
                ", respuestas: " + respuestas;
    }


}
