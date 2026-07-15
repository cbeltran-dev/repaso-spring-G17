-- Docentes
INSERT INTO docentes ( nombre, apellido, especialidad, email) VALUES
( 'Ana',   'Torres',  'Backend',  'ana.torres@codigo.pe'),
( 'Luis',  'Ramírez', 'Frontend', 'luis.ramirez@codigo.pe'),
( 'Marta', 'Silva',   'Backend',  'marta.silva@codigo.pe'),
( 'Diego', 'Vega',    'Data',     'diego.vega@codigo.pe');


-- Estudiantes
INSERT INTO estudiantes ( nombre, apellido, email) VALUES
('Carlos', 'Mendoza',  'carlos.mendoza@mail.com'),
('Sofía',  'Quispe',   'sofia.quispe@mail.com'),
('Jorge',  'Flores',   'jorge.flores@mail.com'),
('Lucía',  'Rojas',    'lucia.rojas@mail.com'),
('Pedro',  'Cárdenas', 'pedro.cardenas@mail.com');


-- Cursos (cada uno con su docente)
INSERT INTO cursos ( nombre, descripcion, duracion_horas, docente_id_fk) VALUES
( 'Spring Boot Fundamentos', 'Introducción a APIs REST con Spring', 40, 1),
('React desde Cero',        'Fundamentos de React y hooks',        35, 2),
('JPA Avanzado',            'Consultas, relaciones y rendimiento', 25, 3);


-- Inscripciones (relación N-M curso ↔ estudiante)
INSERT INTO curso_estudiante (curso_id_fk, estudiante_id_fk) VALUES
(1, 1), (1, 2), (1, 3),
(2, 2), (2, 4),
(3, 3), (3, 5);