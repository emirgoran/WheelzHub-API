# Use an official OpenJDK as a base image
FROM openjdk:17-jdk-slim

# Create an application directory
WORKDIR /app

# Copy the built JAR file into the image
COPY target/*.jar app.jar

# Set the default command to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
