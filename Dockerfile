#https://github.com/TakiRahal/spring-boot-render
FROM eclipse-temurin:17-jdk-alpine
#environment vairable
ENV POSTGRES_HOST=host.docker.internal
ENV POSTGRES_PORT=5432
ENV POSTGRES_USERNAME=postgres
ENV POSTGRES_DBNAME=postgres
ENV POSTGRES_PASSWORD=root
ENV POSTGRES_SSLMODE=disable
ENV GIN_MODE=release
ENV SPRING_PROFILES_ACTIVE=prod

VOLUME /tmp
ARG JAR_FILE
#for maven
COPY target/*.jar app.jar
#for gradle
#COPY build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]