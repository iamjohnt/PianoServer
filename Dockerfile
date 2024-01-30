# Stage 1: Build the Java application with Maven
FROM --platform=arm64 maven:3-amazoncorretto-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN --mount=type=cache,target=/root/.m2 mvn -e clean package -Dmaven.test.skip=true

# # Stage 2: Create an image based on Amazon Corretto 17
FROM --platform=arm64 amazoncorretto:17
WORKDIR /app
COPY --from=build /app/target/ws_server-0.0.1.jar ./app.jar
#COPY src/main/resources ./
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
