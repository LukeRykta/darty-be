FROM ubuntu:latest
LABEL authors="Luke Ryktarsyk, Zoltan Nahoczki"

ENTRYPOINT ["top", "-b"]

# Start from a Java 17 JDK image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy built JAR file
COPY target/spring-boot-darty-0.0.1-SNAPSHOT.jar app.jar

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]