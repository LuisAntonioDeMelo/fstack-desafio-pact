
FROM openjdk:17-jdk-slim as build
RUN apt-get update && apt-get install -y maven

COPY src /app/src
COPY pom.xml /app

WORKDIR /app
RUN mvn clean install  -DskipTests

FROM openjdk:17-jdk-slim
COPY --from=build app/target/vagas-1.0.jar /app/app.jar

WORKDIR /app
EXPOSE 8081

CMD ["java", "-jar", "app.jar"]



