FROM openjdk:17-jdk-alpine
WORKDIR /spring
COPY build/libs/CarmixServer-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","CarmixServer-0.0.1-SNAPSHOT.jar"]
