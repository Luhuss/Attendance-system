# Usa una imagen base de Java 21
# Estapa 1: Construir el JAR con Maven
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Define el directorio de trabajo
# Etapa 2: imagen ligera solo con el JAR
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/attendance-system-0.0.1-SNAPSHOT.jar app.jar

# Copia el JAR generado por Maven
# COPY target/attendance-system-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]


# Estapa 1: (build) usa Maven dentro del contenedor para compilar tu proyecto y generar el JAR
# Etapa 2: (runtime) copia solo el JAR a una imagen limpia de Java 21 y lo ejecuta.
# Esto se llama multi-stage build y evota que tu imagen final tenga Maven instalado, quedando más liviana.
