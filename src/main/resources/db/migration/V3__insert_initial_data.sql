-- V3__insert_initial_data.sql
-- Data iniciales para el sistema de Asitencia Escolar en Tiempo Real

-- Insertar roles básicos
INSERT INTO roles (name, description) VALUES
                                          ('ADMIN', 'Administrado del sistema con acceso completo'),
                                          ('TEACHER', 'Profesor encargado de cursos y asitencia'),
                                          ('INSPECTOR', 'Inspector encargado de validar asistencia');

-- Insertar usuario administrador inicial
INSERT INTO users (username, password, email, role_id)
VALUES ('admin', 'admin123', 'admin@school.com',
        (SELECT id FROM roles WHERE name = 'ADMIN'));

-- Insertar curso de prueba
INSERT INTO courses (name, description, teacher_id)
VALUES ('Matemáticas Básicas', 'Curso introductorio de matemáticas',
        (SELECT id FROM users WHERE username = 'admin'));

-- Insertar asistencia de prueba
INSERT INTO attendance (student_id, course_id, status)
VALUES (
        (SELECT id FROM student WHERE email = 'juan.perez@school.com'),
        (SELECT id FROM courses WHERE name = 'Matemáticas Básicas'),
        'PRESENT'
);


