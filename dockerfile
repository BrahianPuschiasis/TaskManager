# 1. Usar una imagen base de Maven con OpenJDK 17
FROM maven:3.9.9-eclipse-temurin-17 AS build

# 2. Configurar el directorio de trabajo
WORKDIR /app

# 3. Copiar el archivo pom.xml y resolver dependencias
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# 4. Copiar el código fuente y compilar la aplicación
COPY src ./src
RUN mvn clean package -DskipTests

# 5. Segunda fase: usar OpenJDK y copiar solo el JAR final
FROM openjdk:17-jdk-slim
WORKDIR /app

# 6. Copiar el JAR generado en la fase anterior
COPY --from=build /app/target/*.jar app.jar

# 7. Exponer el puerto
EXPOSE 5167

# 8. Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
