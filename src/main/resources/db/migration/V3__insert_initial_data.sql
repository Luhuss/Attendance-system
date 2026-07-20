-- V3__insert_initial_data.sql
-- Datos iniciales para el Sistema de Asistencia Escolar en Tiempo Real

-- Insertar roles básicos
INSERT INTO roles (name, description) VALUES
                                          ('ADMIN', 'Administrador del sistema con acceso completo'),
                                          ('TEACHER', 'Profesor encargado de cursos y asistencia'),
                                          ('INSPECTOR', 'Inspector encargado de validar asistencia');

-- Insertar usuario administrador inicial con contraseña encriptada (BCrypt de 'admin123')
INSERT INTO users (username, password, email, role_id)
VALUES (
           'admin',
           '$2a$10$7QJHhXQkzQhQ9JkYwYyF5eQnPZCkYwYyF5eQnPZCkYwYyF5eQnPZCk', -- hash BCrypt
           'admin@school.com',
           (SELECT id FROM roles WHERE name = 'ADMIN')
       );

-- Insertar curso de prueba asignado al admin
INSERT INTO courses (name, description, teacher_id)
VALUES (
           'Matemáticas Básicas',
           'Curso introductorio de matemáticas',
           (SELECT id FROM users WHERE username = 'admin')
       );

-- Insertar asistencia de prueba
INSERT INTO attendance (student_id, course_id, status)
VALUES (
           (SELECT id FROM students WHERE email = 'juan.perez@school.com'),
           (SELECT id FROM courses WHERE name = 'Matemáticas Básicas'),
           'PRESENT'
       );
