CREATE TABLE topicos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(200) NOT NULL,
    mensaje VARCHAR(500) NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    status ENUM("ACTIVO", "INACTIVO", "RESUELTO") NOT NULL,
    autor BIGINT NOT NULL,
    curso BIGINT NOT NULL,
    respuestas INT DEFAULT 0,
    FOREIGN KEY (autor) REFERENCES usuarios(id),
    FOREIGN KEY (curso) REFERENCES cursos(id)
);
