# Stage 1: Build the Spring Boot application
FROM ubuntu:22.04 AS build

# Install Maven and OpenJDK 17
RUN apt-get update && apt-get install -y maven openjdk-17-jdk

# Set working directory
WORKDIR /app

# Copy Maven project files
COPY backend/pom.xml .
COPY backend/src ./src

# Build the project (skip tests)
RUN mvn clean package -DskipTests

# Stage 2: Run the Spring Boot application
FROM ubuntu:22.04

# Install OpenJDK 17
RUN apt-get update && apt-get install -y openjdk-17-jdk

# Set working directory
WORKDIR /app

# Copy the built JAR file
COPY --from=build /app/target/*.jar app.jar

# Expose port 8000
EXPOSE 8000

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
