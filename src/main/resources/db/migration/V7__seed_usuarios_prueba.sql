-- Usuarios de prueba con datos financieros desde enero de 2025 hasta mayo de 2026.
-- Contrasena comun para todos los usuarios de prueba: Password1!

CREATE TEMPORARY TABLE seed_usuarios_prueba (
    email VARCHAR(150) PRIMARY KEY,
    salario DECIMAL(12,2) NOT NULL,
    vivienda DECIMAL(12,2) NOT NULL,
    alimentacion DECIMAL(12,2) NOT NULL,
    transporte DECIMAL(12,2) NOT NULL,
    suministros DECIMAL(12,2) NOT NULL,
    ocio DECIMAL(12,2) NOT NULL,
    salud DECIMAL(12,2) NOT NULL,
    educacion DECIMAL(12,2) NOT NULL,
    freelance DECIMAL(12,2) NOT NULL,
    prestacion DECIMAL(12,2) NOT NULL
);

INSERT INTO seed_usuarios_prueba (
    email, salario, vivienda, alimentacion, transporte, suministros,
    ocio, salud, educacion, freelance, prestacion
) VALUES
    ('ana.garcia@test.local', 2450.00, 820.00, 470.00, 130.00, 185.00, 170.00, 95.00, 210.00, 450.00, 0.00),
    ('luis.martin@test.local', 1980.00, 690.00, 360.00, 105.00, 145.00, 130.00, 80.00, 0.00, 300.00, 0.00),
    ('carmen.lopez@test.local', 1580.00, 560.00, 290.00, 85.00, 115.00, 95.00, 70.00, 0.00, 220.00, 0.00),
    ('javier.ruiz@test.local', 2150.00, 730.00, 385.00, 155.00, 160.00, 150.00, 90.00, 0.00, 380.00, 0.00),
    ('marta.sanchez@test.local', 1780.00, 650.00, 410.00, 95.00, 135.00, 110.00, 75.00, 180.00, 180.00, 160.00);

INSERT INTO usuarios (email, password_hash, rol, activo, debe_cambiar_password)
SELECT
    s.email,
    '$2a$10$z18MJj8xkk68RyNjQaJQDeh09XKbsS.9AeBz7R0n3kNisMrZFhJ5O',
    'USUARIO',
    TRUE,
    FALSE
FROM seed_usuarios_prueba s
WHERE NOT EXISTS (
    SELECT 1
    FROM usuarios u
    WHERE u.email = s.email
);

CREATE TEMPORARY TABLE seed_categorias_prueba (
    nombre VARCHAR(60) NOT NULL,
    tipo VARCHAR(20) NOT NULL
);

INSERT INTO seed_categorias_prueba (nombre, tipo) VALUES
    ('Nomina', 'INGRESO'),
    ('Freelance', 'INGRESO'),
    ('Prestacion familiar', 'INGRESO'),
    ('Vivienda', 'GASTO'),
    ('Alimentacion', 'GASTO'),
    ('Transporte', 'GASTO'),
    ('Suministros', 'GASTO'),
    ('Ocio', 'GASTO'),
    ('Salud', 'GASTO'),
    ('Educacion', 'GASTO');

INSERT INTO categorias (usuario_id, nombre, tipo, activa)
SELECT u.id, c.nombre, c.tipo, TRUE
FROM seed_usuarios_prueba s
JOIN usuarios u ON u.email = s.email
CROSS JOIN seed_categorias_prueba c
WHERE NOT EXISTS (
    SELECT 1
    FROM categorias existente
    WHERE existente.usuario_id = u.id
      AND existente.nombre = c.nombre
      AND existente.tipo = c.tipo
);

CREATE TEMPORARY TABLE seed_meses_prueba (
    mes_inicio DATE PRIMARY KEY
);

INSERT INTO seed_meses_prueba (mes_inicio) VALUES
    ('2025-01-01'), ('2025-02-01'), ('2025-03-01'), ('2025-04-01'),
    ('2025-05-01'), ('2025-06-01'), ('2025-07-01'), ('2025-08-01'),
    ('2025-09-01'), ('2025-10-01'), ('2025-11-01'), ('2025-12-01'),
    ('2026-01-01'), ('2026-02-01'), ('2026-03-01'), ('2026-04-01'),
    ('2026-05-01');

INSERT INTO ingresos (usuario_id, categoria_id, importe, fecha, descripcion)
SELECT
    u.id,
    c.id,
    s.salario,
    DATE_ADD(m.mes_inicio, INTERVAL 4 DAY),
    CONCAT('Nomina mensual ', DATE_FORMAT(m.mes_inicio, '%Y-%m'))
FROM seed_usuarios_prueba s
JOIN usuarios u ON u.email = s.email
JOIN categorias c ON c.usuario_id = u.id AND c.nombre = 'Nomina' AND c.tipo = 'INGRESO'
JOIN seed_meses_prueba m;

INSERT INTO ingresos (usuario_id, categoria_id, importe, fecha, descripcion)
SELECT
    u.id,
    c.id,
    s.freelance,
    DATE_ADD(m.mes_inicio, INTERVAL 5 DAY),
    CONCAT('Trabajo freelance ', DATE_FORMAT(m.mes_inicio, '%Y-%m'))
FROM seed_usuarios_prueba s
JOIN usuarios u ON u.email = s.email
JOIN categorias c ON c.usuario_id = u.id AND c.nombre = 'Freelance' AND c.tipo = 'INGRESO'
JOIN seed_meses_prueba m
WHERE s.freelance > 0
  AND MONTH(m.mes_inicio) IN (3, 6, 9, 12);

INSERT INTO ingresos (usuario_id, categoria_id, importe, fecha, descripcion)
SELECT
    u.id,
    c.id,
    s.prestacion,
    DATE_ADD(m.mes_inicio, INTERVAL 5 DAY),
    CONCAT('Prestacion familiar ', DATE_FORMAT(m.mes_inicio, '%Y-%m'))
FROM seed_usuarios_prueba s
JOIN usuarios u ON u.email = s.email
JOIN categorias c ON c.usuario_id = u.id AND c.nombre = 'Prestacion familiar' AND c.tipo = 'INGRESO'
JOIN seed_meses_prueba m
WHERE s.prestacion > 0;

INSERT INTO gastos (usuario_id, categoria_id, importe, fecha, descripcion)
SELECT
    u.id,
    c.id,
    s.vivienda,
    DATE_ADD(m.mes_inicio, INTERVAL 2 DAY),
    CONCAT('Vivienda ', DATE_FORMAT(m.mes_inicio, '%Y-%m'))
FROM seed_usuarios_prueba s
JOIN usuarios u ON u.email = s.email
JOIN categorias c ON c.usuario_id = u.id AND c.nombre = 'Vivienda' AND c.tipo = 'GASTO'
JOIN seed_meses_prueba m;

INSERT INTO gastos (usuario_id, categoria_id, importe, fecha, descripcion)
SELECT
    u.id,
    c.id,
    s.alimentacion,
    DATE_ADD(m.mes_inicio, INTERVAL 3 DAY),
    CONCAT('Compra y alimentacion ', DATE_FORMAT(m.mes_inicio, '%Y-%m'))
FROM seed_usuarios_prueba s
JOIN usuarios u ON u.email = s.email
JOIN categorias c ON c.usuario_id = u.id AND c.nombre = 'Alimentacion' AND c.tipo = 'GASTO'
JOIN seed_meses_prueba m;

INSERT INTO gastos (usuario_id, categoria_id, importe, fecha, descripcion)
SELECT
    u.id,
    c.id,
    s.transporte,
    DATE_ADD(m.mes_inicio, INTERVAL 4 DAY),
    CONCAT('Transporte ', DATE_FORMAT(m.mes_inicio, '%Y-%m'))
FROM seed_usuarios_prueba s
JOIN usuarios u ON u.email = s.email
JOIN categorias c ON c.usuario_id = u.id AND c.nombre = 'Transporte' AND c.tipo = 'GASTO'
JOIN seed_meses_prueba m;

INSERT INTO gastos (usuario_id, categoria_id, importe, fecha, descripcion)
SELECT
    u.id,
    c.id,
    s.suministros,
    DATE_ADD(m.mes_inicio, INTERVAL 5 DAY),
    CONCAT('Suministros ', DATE_FORMAT(m.mes_inicio, '%Y-%m'))
FROM seed_usuarios_prueba s
JOIN usuarios u ON u.email = s.email
JOIN categorias c ON c.usuario_id = u.id AND c.nombre = 'Suministros' AND c.tipo = 'GASTO'
JOIN seed_meses_prueba m;

INSERT INTO gastos (usuario_id, categoria_id, importe, fecha, descripcion)
SELECT
    u.id,
    c.id,
    s.ocio,
    DATE_ADD(m.mes_inicio, INTERVAL 5 DAY),
    CONCAT('Ocio ', DATE_FORMAT(m.mes_inicio, '%Y-%m'))
FROM seed_usuarios_prueba s
JOIN usuarios u ON u.email = s.email
JOIN categorias c ON c.usuario_id = u.id AND c.nombre = 'Ocio' AND c.tipo = 'GASTO'
JOIN seed_meses_prueba m
WHERE MONTH(m.mes_inicio) IN (2, 4, 6, 8, 10, 12);

INSERT INTO gastos (usuario_id, categoria_id, importe, fecha, descripcion)
SELECT
    u.id,
    c.id,
    s.salud,
    DATE_ADD(m.mes_inicio, INTERVAL 5 DAY),
    CONCAT('Salud ', DATE_FORMAT(m.mes_inicio, '%Y-%m'))
FROM seed_usuarios_prueba s
JOIN usuarios u ON u.email = s.email
JOIN categorias c ON c.usuario_id = u.id AND c.nombre = 'Salud' AND c.tipo = 'GASTO'
JOIN seed_meses_prueba m
WHERE MONTH(m.mes_inicio) IN (1, 5, 9);

INSERT INTO gastos (usuario_id, categoria_id, importe, fecha, descripcion)
SELECT
    u.id,
    c.id,
    s.educacion,
    DATE_ADD(m.mes_inicio, INTERVAL 5 DAY),
    CONCAT('Educacion ', DATE_FORMAT(m.mes_inicio, '%Y-%m'))
FROM seed_usuarios_prueba s
JOIN usuarios u ON u.email = s.email
JOIN categorias c ON c.usuario_id = u.id AND c.nombre = 'Educacion' AND c.tipo = 'GASTO'
JOIN seed_meses_prueba m
WHERE s.educacion > 0
  AND MONTH(m.mes_inicio) IN (1, 2, 9, 10);

INSERT INTO familias (usuario_id, estado_civil)
SELECT u.id,
       CASE s.email
           WHEN 'ana.garcia@test.local' THEN 'CASADO'
           WHEN 'luis.martin@test.local' THEN 'PAREJA_DE_HECHO'
           WHEN 'marta.sanchez@test.local' THEN 'DIVORCIADO'
       END
FROM seed_usuarios_prueba s
JOIN usuarios u ON u.email = s.email
WHERE s.email IN (
    'ana.garcia@test.local',
    'luis.martin@test.local',
    'marta.sanchez@test.local'
)
AND NOT EXISTS (
    SELECT 1
    FROM familias f
    WHERE f.usuario_id = u.id
);

CREATE TEMPORARY TABLE seed_miembros_prueba (
    email VARCHAR(150) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    nombre VARCHAR(80) NOT NULL,
    ingreso DECIMAL(12,2) NULL
);

INSERT INTO seed_miembros_prueba (email, tipo, nombre, ingreso) VALUES
    ('ana.garcia@test.local', 'PAREJA', 'David', 1850.00),
    ('ana.garcia@test.local', 'HIJO', 'Lucia', 0.00),
    ('luis.martin@test.local', 'PAREJA', 'Nerea', 1450.00),
    ('marta.sanchez@test.local', 'HIJO', 'Diego', 0.00);

INSERT INTO miembros_familiares (familia_id, tipo, nombre, ingreso)
SELECT f.id, m.tipo, m.nombre, m.ingreso
FROM seed_miembros_prueba m
JOIN usuarios u ON u.email = m.email
JOIN familias f ON f.usuario_id = u.id
WHERE NOT EXISTS (
    SELECT 1
    FROM miembros_familiares existente
    WHERE existente.familia_id = f.id
      AND existente.tipo = m.tipo
      AND existente.nombre = m.nombre
);

DROP TEMPORARY TABLE IF EXISTS seed_miembros_prueba;
DROP TEMPORARY TABLE IF EXISTS seed_meses_prueba;
DROP TEMPORARY TABLE IF EXISTS seed_categorias_prueba;
DROP TEMPORARY TABLE IF EXISTS seed_usuarios_prueba;
