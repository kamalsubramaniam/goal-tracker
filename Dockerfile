FROM gradle:8-jdk17 AS builder
WORKDIR /home/gradle/project

COPY build.gradle.kts settings.gradle.kts ./
COPY src ./src

RUN gradle build --no-daemon -x test


FROM eclipse-temurin:17-jre
WORKDIR /app
# Copy only the built jar from the builder stage
COPY --from=builder /home/gradle/project/build/libs/goal-tracker-0.0.1-SNAPSHOT.jar app.jar
# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
