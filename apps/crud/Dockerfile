## Build ##
# base image
FROM maven:3-openjdk-16-slim AS build

# set the working directory
WORKDIR /opt/app
# copy contents
COPY ./ /opt/app
#build
RUN mvn clean install -DskipTests

## Run time ##
# base image
FROM openjdk:8-jdk-alpine
#copy the jar
COPY --from=build /opt/app/target/*.jar app.jar
#set & expose port
ENV PORT 8081
EXPOSE $PORT
#run the jar, set heap size, pass port to app
ENTRYPOINT ["java","-jar","-Xmx1024M","-Dserver.port=${PORT}","app.jar"]
