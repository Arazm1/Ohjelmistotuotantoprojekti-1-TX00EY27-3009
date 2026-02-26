FROM maven:3.9.6-eclipse-temurin-21 AS build
LABEL authors="fivz"

WORKDIR /app

COPY pom.xml .

copy . /app
RUN mvn package

CMD ["java", "-jar", "target/laskin.jar"]