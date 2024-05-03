FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN maven clean package -Dmaven.test.skip=true

FROM openjdk:17-jdk-slim
COPY --from=build /target/caloriecounter-0.0.1-SNAPSHOT.jar caloriecounter.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "caloriecounter.jar"]
