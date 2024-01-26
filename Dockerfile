FROM openjdk:17-jdk-alpine3.14
COPY ./build/libs/auth-service-1.0.1.jar auth-service.jar
ENTRYPOINT ["java", "-jar", "auth-service.jar"]
