FROM openjdk:8-jdk-alpine

RUN mkdir -p /app
WORKDIR /app

COPY target/springboot-oss-0.0.1-SNAPSHOT.jar app.jar

COPY src/main/resources/application.yml /config/application.yml

ARG ALIYUN_ACCESS_KEY_ID
ARG ALIYUN_ACCESS_KEY_SECRET

ENV ALIYUN_ACCESS_KEY_ID=${ALIYUN_ACCESS_KEY_ID}
ENV ALIYUN_ACCESS_KEY_SECRET=${ALIYUN_ACCESS_KEY_SECRET}

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.location=classpath:/application.yml,/config/application.yml"]

EXPOSE 8080

