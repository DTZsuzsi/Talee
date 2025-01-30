# First stage: Build
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app

# Copy source code and pom.xml
COPY ./src ./src
COPY pom.xml ./

# Build the application
RUN mvn clean package -DskipTests

# Second stage: Runtime
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy only the built JAR file from the build stage
COPY --from=build /app/target/server-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]