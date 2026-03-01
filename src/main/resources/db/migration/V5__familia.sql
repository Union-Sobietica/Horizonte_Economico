CREATE TABLE familias (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          usuario_id BIGINT NOT NULL UNIQUE,
                          estado_civil VARCHAR(30) NOT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          CONSTRAINT fk_familias_usuario
                              FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
                                  ON DELETE CASCADE
);

CREATE TABLE miembros_familiares (
                                     id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                     familia_id BIGINT NOT NULL,
                                     tipo VARCHAR(20) NOT NULL,
                                     nombre VARCHAR(80) NOT NULL,
                                     ingreso DECIMAL(12,2) NULL,
                                     created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                     CONSTRAINT fk_miembros_familia
                                         FOREIGN KEY (familia_id) REFERENCES familias(id)
                                             ON DELETE CASCADE
);

CREATE INDEX idx_miembros_familia ON miembros_familiares(familia_id);