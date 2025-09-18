# Use a lightweight Java runtime
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR built by Gradle into the container
COPY build/libs/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "webservice-uppgift-0.0.1-SNAPSHOT.jar"]
