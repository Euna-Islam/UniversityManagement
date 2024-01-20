FROM maven:3-openjdk-16-slim AS build

WORKDIR /opt/app

COPY ./ /opt/app
RUN mvn clean install -DskipTests

FROM openjdk:8-jdk-alpine

COPY --from=build /opt/app/target/*.jar app.jar

ENV PORT 8081
EXPOSE $PORT

ENTRYPOINT ["java","-jar","-Xmx1024M","-Dserver.port=${PORT}","app.jar"]
