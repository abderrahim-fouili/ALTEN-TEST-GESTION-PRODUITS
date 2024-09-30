FROM maven:3.8.5-openjdk-17 AS build

COPY . /ALTEN-TEST-GESTION-PRODUITS

WORKDIR /ALTEN-TEST-GESTION-PRODUITS

RUN mvn clean install -DskipTests

FROM openjdk:17-alpine

COPY --from=build /ALTEN-TEST-GESTION-PRODUITS/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]