#FROM openjdk:22
#
#WORKDIR /app
#
#COPY target/BookService.jar app.jar
#
#EXPOSE 8080
#
#ENTRYPOINT ["java", "-jar", "app.jar"]

# Use an official OpenJDK runtime as a parent image
#FROM eclipse-temurin:22-jdk-jammy
#
## Set the working directory in the container
#WORKDIR /app
#
## Copy the executable JAR file into the container
#COPY target/your-app-name.jar app.jar
#
## Make port 8080 available to the world outside this container
#EXPOSE 8080
#
## Run the JAR file
#ENTRYPOINT ["java", "-jar", "app.jar"]
FROM openjdk:22
VOLUME /tmp
EXPOSE 8080
COPY target/BookService.jar recipe.jar
ENTRYPOINT ["java","-jar","/recipe.jar"]