# Build stage
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copy gradle wrapper and check directory content
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./
RUN ls -la

# Set executable permissions and check gradlew execution
RUN chmod +x ./gradlew && ./gradlew --version

# Pre-fetch all dependencies
RUN ./gradlew --no-daemon dependencies

# Copy the project source and build
COPY src src
RUN ./gradlew clean build -x test --no-daemon

# Run stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
