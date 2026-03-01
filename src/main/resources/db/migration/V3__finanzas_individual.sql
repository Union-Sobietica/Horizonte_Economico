CREATE TABLE categorias (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            usuario_id BIGINT NOT NULL,
                            nombre VARCHAR(60) NOT NULL,
                            tipo VARCHAR(20) NOT NULL,
                            activa BOOLEAN NOT NULL DEFAULT TRUE,
                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            CONSTRAINT fk_categorias_usuario
                                FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
                                    ON DELETE CASCADE,
                            CONSTRAINT uq_categoria_usuario_nombre_tipo
                                UNIQUE (usuario_id, nombre, tipo)
);

CREATE INDEX idx_categorias_usuario ON categorias(usuario_id);

CREATE TABLE ingresos (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          usuario_id BIGINT NOT NULL,
                          importe DECIMAL(12,2) NOT NULL,
                          fecha DATE NOT NULL,
                          fuente VARCHAR(120) NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          CONSTRAINT fk_ingresos_usuario
                              FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
                                  ON DELETE CASCADE
);

CREATE INDEX idx_ingresos_usuario_fecha ON ingresos(usuario_id, fecha);

CREATE TABLE gastos (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        usuario_id BIGINT NOT NULL,
                        categoria_id BIGINT NOT NULL,
                        importe DECIMAL(12,2) NOT NULL,
                        fecha DATE NOT NULL,
                        descripcion VARCHAR(200) NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        CONSTRAINT fk_gastos_usuario
                            FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
                                ON DELETE CASCADE,
                        CONSTRAINT fk_gastos_categoria
                            FOREIGN KEY (categoria_id) REFERENCES categorias(id)
                                ON DELETE RESTRICT
);

CREATE INDEX idx_gastos_usuario_fecha ON gastos(usuario_id, fecha);
CREATE INDEX idx_gastos_usuario_categoria ON gastos(usuario_id, categoria_id);