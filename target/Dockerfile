FROM openjdk:8-jdk-alpine
ARG JAR_FILE=bootcamp-api.jar
COPY ${JAR_FILE} app.jar
COPY deploy deploy
ENTRYPOINT ["java","-Dspring.profiles.active=dev",-jar","/app.jar"]