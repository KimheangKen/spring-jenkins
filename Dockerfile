# Fetching latest version of Java
FROM openjdk:22-ea-17-jdk-slim

# Setting up work directory
WORKDIR /app

# Copy the jar file into our app
COPY ./target/HOMEWORK003-0.0.1-SNAPSHOT.jar /app

# Exposing port 8080
EXPOSE 8080

# Starting the application
CMD ["java", "-jar", "HOMEWORK003-0.0.1-SNAPSHOT.jar"]
