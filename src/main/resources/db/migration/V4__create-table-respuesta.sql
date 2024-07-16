CREATE TABLE respuestas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    mensaje TEXT NOT NULL,
    topico BIGINT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    autor BIGINT NOT NULL,
    solucion VARCHAR(500) NOT NULL,
    FOREIGN KEY (topico) REFERENCES topicos(id),
    FOREIGN KEY (autor) REFERENCES usuarios(id)
);
