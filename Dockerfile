# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /appbooking

# Copy the packaged jar file into the container
COPY target/booking-0.0.1-SNAPSHOT.jar appbooking.jar

# Make port 8080 available to the world outside this container
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java","-jar","appbooking.jar"]