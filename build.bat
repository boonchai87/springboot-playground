mvn clean package -Pprod -DskipTests
docker build -t boonchai/spring-boot-render .