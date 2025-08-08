FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY ./target/usuarios-backend-0.0.1-SNAPSHOT.jar .

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "usuarios-backend-0.0.1-SNAPSHOT.jar"]