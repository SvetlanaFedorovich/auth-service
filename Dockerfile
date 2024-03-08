FROM openjdk:17-jdk-alpine3.14
COPY ./build/libs/auth-service-1.0.1.jar auth-service.jar
#ENV GRPC_CLIENT_KEYCLOAK_SERVICE_ADDRESS=static://minikube.mshome.net:31500
ENTRYPOINT ["java", "-jar", "auth-service.jar"]
