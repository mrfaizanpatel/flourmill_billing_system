# Use a base image with OpenJDK
FROM openjdk:17-jdk-slim as build

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper and pom.xml
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .

# Download Maven dependencies (this will cache them if unchanged)
RUN ./mvnw dependency:go-offline

# Copy the source code into the container
COPY src ./src

# Package the app into a JAR file using Maven
RUN ./mvnw clean package -DskipTests

# Create a smaller runtime image
FROM openjdk:17-jdk-slim

# Set the working directory for the runtime image
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose port 8000 for the Spring Boot app
EXPOSE 8000

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
