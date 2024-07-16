package com.aluracursos.forohub.infra.errores;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class TratadorDeErrores {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ErrorDeValidacion.class)
    public ResponseEntity tratarErroresIntegridad(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorValidacionesNegocio(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Valor inválido: " + ex.getValue());
    }

    private record DatosErrorValidacion(String campo, String error){
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
