FROM openjdk:8-jdk-alpine

RUN mkdir -p /app
WORKDIR /app

COPY target/springboot-oss-0.0.1-SNAPSHOT.jar app.jar

COPY src/main/resources/application.yml /config/application.yml

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=classpath:/application.yml,/config/application.yml"]

EXPOSE 8080
