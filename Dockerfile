# Usa una imagen base de Java
FROM eclipse-temurin:17-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR generado en el contenedor
COPY target/Tienda_CH-0.0.1-SNAPSHOT.jar app.jar

# Configura el perfil activo para Docker
ENV SPRING_PROFILES_ACTIVE=docker

# Expone el puerto que tu aplicación usa (cambia 8080 si usas otro)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
