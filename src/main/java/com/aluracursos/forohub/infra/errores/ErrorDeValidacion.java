package com.aluracursos.forohub.infra.errores;

public class ErrorDeValidacion extends RuntimeException {
    public ErrorDeValidacion(String s){
        super(s);
    }
}
