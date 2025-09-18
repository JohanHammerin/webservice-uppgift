# ---------- Build Stage ----------

FROM gradle:8.3-jdk21 AS build

# Sätt arbetsmapp

WORKDIR /app

# Kopiera hela projektet

COPY . .

# Bygg JAR-filen

RUN gradle clean build -x test

# ---------- Run Stage ----------

FROM openjdk:21-jdk-slim

# Sätt arbetsmapp

WORKDIR /app

# Kopiera JAR-filen från build-staget

COPY --from=build /app/build/libs/*.jar app.jar

# Exponera porten som Spring Boot använder

EXPOSE 8080

# Kör applikationen

ENTRYPOINT ["java", "-jar", "app.jar"]

 