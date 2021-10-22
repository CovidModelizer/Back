FROM openjdk:8-jre-alpine

WORKDIR /app

COPY target/*.jar run.jar

ENTRYPOINT ["java", "-jar", "run.jar", "--server.port=443"]
