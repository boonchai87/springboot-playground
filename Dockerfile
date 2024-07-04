FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
#for maven
COPY target/*.jar app.jar
#for gradle
#COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]