# Use an official Maven image to build the project
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and source code to the container
COPY pom.xml .
COPY src ./src

# Package the application without compiling or running tests
RUN mvn clean package -Dmaven.test.skip=true

# Use a lighter OpenJDK image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory for the runtime container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/exam-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the application port
EXPOSE 8081

# Set the default command to run the JAR
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
