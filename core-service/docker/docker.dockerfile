# --- ETAPA 1: Construcción (Build) ---
# Usamos una imagen de Maven con JDK 17 para compilar el código
FROM maven:3.8.5-openjdk-17-slim AS build

# Definimos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el archivo pom.xml para descargar las dependencias primero (optimiza caché)
COPY pom.xml .

# Descargamos las dependencias de Maven (esto se cachea si el pom no cambia)
RUN mvn dependency:go-offline -B

# Copiamos el código fuente
COPY src ./src

# Compilamos el proyecto y generamos el .jar (saltando los tests para acelerar el build)
RUN mvn clean package -DskipTests

# --- ETAPA 2: Ejecución (Runtime) ---
# Usamos una imagen mucho más pequeña que solo tiene el JRE (Runtime)
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copiamos solo el archivo .jar generado en la etapa anterior
# Nota: Asegúrate de que el nombre del .jar coincida con el de tu pom.xml
COPY --from=build /app/target/*.jar health-api.jar

# Exponemos el puerto en el que corre Spring Boot
EXPOSE 8080

# Definimos las variables de entorno por defecto (pueden ser sobreescritas al correr el contenedor)
ENV DB_URL=""
ENV DB_USER=""
ENV DB_PASSWORD=""

# Comando para ejecutar la aplicación al iniciar el contenedor
ENTRYPOINT ["java", "-jar", "health-api.jar"]