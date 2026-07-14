-- V2__add_constraints.sql
-- Restricciones e índices para reforzar integridad

-- Evitar emails duplicados en usuarios y estudiantes
ALTER TABLE users
    ADD CONSTRAINT uq_users_email UNIQUE (email);

ALTER TABLE students
    ADD CONSTRAINT uq_students_email UNIQUE (email);

-- Validar estado de asistencia (solo valores permitidos)
ALTER TABLE attendance
    ADD CONSTRAINT chk_attendance_status CHECK ( status IN ('PRESENT', 'ABSENT', 'LATE'));

-- Índices para mejorar consultas frecuentes
CREATE INDEX idx_attendance_student ON attendance(student_id);
CREATE INDEX idx_attendance_course ON attendance(course_id);
CREATE INDEX idx_attendance_teacher ON courses(teacher_id);

-- Asegurar que un curso tenga siempre un profesor asignado
ALTER TABLE courses
    ALTER COLUMN teacher_id SET NOT NULL;