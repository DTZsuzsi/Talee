# First stage: Build
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app

COPY ./src ./src
COPY pom.xml ./

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY --from=build /app/target/server-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]