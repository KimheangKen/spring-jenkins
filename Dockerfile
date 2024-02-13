# Fetching latest version of Maven and Java
FROM maven:3.8.4-openjdk-17-slim AS build

# Copying the source code to the container
COPY . /usr/src/app

# Setting the working directory
WORKDIR /usr/src/app

# Building the application with Maven
RUN mvn clean package

# Building the final image
FROM openjdk:17-slim

# Setting up work directory
WORKDIR /app

# Copying the built JAR file from the previous stage
COPY --from=build /usr/src/app/target/HOMEWORK003-0.0.1-SNAPSHOT.jar /app/HOMEWORK003-0.0.1-SNAPSHOT.jar

# Exposing port 8080
EXPOSE 8080

# Starting the application
CMD ["java", "-jar", "HOMEWORK003-0.0.1-SNAPSHOT.jar"]
