# ---------- Build Stage ----------

FROM gradle:8.3-jdk21-slim AS build

WORKDIR /app

# Kopiera alla filer

COPY . .

# Bygg JAR utan tester

RUN gradle clean build -x test

# ---------- Runtime Stage ----------

FROM openjdk:21-jdk-slim

WORKDIR /app

# Kopiera JAR från build-stadiet

COPY --from=build /app/build/libs/*.jar app.jar

# Exponera porten som Spring Boot använder

EXPOSE 8080

# Starta applikationen

ENTRYPOINT ["java", "-jar", "app.jar"]

 