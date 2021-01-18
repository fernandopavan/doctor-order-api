FROM openjdk:8-jdk-alpine

EXPOSE 8080

ARG JAR_FILE=target/doctor-order-api-*.jar

WORKDIR /opt/app

COPY ${JAR_FILE} doctor-order-api.jar

ENTRYPOINT ["java","-jar","doctor-order-api.jar"]