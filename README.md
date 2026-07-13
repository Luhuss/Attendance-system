# Attendance System 📚

Sistema de asistencia escolar en tiempo real desarrollado con **Java 21**, **Spring Boot 4.1.0**, **Docker**, **PostgreSQL**, **WebSockets**, **JWT** y siguiendo principios de **Clean Architecture**.

---

## 🚀 Perfiles de Spring Boot

- **dev** → Conexión a PostgreSQL local, configuración simple.
- **test** → Usa H2 en memoria para pruebas unitarias.
- **prod** → Conexión a PostgreSQL en Docker, variables de entorno seguras.

Ejemplo de activación:
```bash
SPRING_PROFILES_ACTIVE=dev
SPRING_PROFILES_ACTIVE=test
SPRING_PROFILES_ACTIVE=prod

🐳 Docker Compose

Levantar el entorno completo: 

docker-compose up -d --build

Servicios:

attendance-system → aplicación Spring Boot en puerto 8080.

postgres → base de datos en puerto 5434 (host) → 5432 (contenedor).

📂 Migraciones Flyway
Scripts en src/main/resources/db/migration:

V1__create_tables.sql → tablas iniciales (usuarios, roles, cursos, alumnos).

V2__add_constraints.sql → llaves foráneas y restricciones.

🔧 Comandos útiles
Ver logs de la app:

docker logs attendance-system

Conectar a la base:

psql -h localhost -p 5434 -U attendance_user -d attendance_db


📖 Buenas prácticas
Mantener consistencia en commits (feat:, fix:, docs:).

Documentar perfiles y puertos en este README.

Usar docker-compose.override.yml para diferenciar desarrollo y producción.

