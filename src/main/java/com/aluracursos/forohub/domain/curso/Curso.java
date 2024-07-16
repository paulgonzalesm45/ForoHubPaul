package com.aluracursos.forohub.domain.curso;

import com.aluracursos.forohub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Curso")
@Table(name = "cursos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;

    @Enumerated(EnumType.STRING)
    private CategoriaCurso categoria;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Topico> topicos;

    private boolean activo;

    public Curso(DatosCurso curso) {
        this.nombre = curso.nombre();
        this.categoria = curso.categoria();
        this.activo = true;
    }

    public void actualizarCurso(DatosActualizarCurso datos) {
        if (datos.nombre() != null){
            this.nombre = datos.nombre();
        }
        if (datos.categoria() != null){
            this.categoria = datos.categoria();
        }
    }

    public void deactivar() {
        this.activo = false;
    }
}
