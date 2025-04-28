# Stage 1: Build Spring Boot app
FROM ubuntu:22.04 AS build

# Install Maven and OpenJDK 17
RUN apt-get update && apt-get install -y maven openjdk-17-jdk

# Set working directory
WORKDIR /app

# Copy Maven project files
COPY atta-chakki/pom.xml .
COPY atta-chakki/src ./src

# Build app without running tests
RUN mvn clean package -DskipTests

# Stage 2: Run Spring Boot app
FROM ubuntu:22.04

# Install OpenJDK 17
RUN apt-get update && apt-get install -y openjdk-17-jdk

# Set working directory
WORKDIR /app

# Copy the built jar file
COPY --from=build /app/target/*.jar app.jar

# Expose port 8000 inside container
EXPOSE 8000

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
