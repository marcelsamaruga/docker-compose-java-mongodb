Java example running with MongoDB in a docker-compose file
============
# Dockerfile
The Dockerfile will create a docker image that will compile the Java application, gets the source and run the maven command to build the app.

## Build stage
> FROM maven:3.6.0-jdk-11-slim AS build

> COPY src /home/app/src

> COPY pom.xml /home/app

> RUN mvn -f /home/app/pom.xml clean package

## Package stage
> FROM openjdk:11-jre-slim

> COPY --from=build /home/app/target/demo-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar

> EXPOSE 8080

> ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]



Note: Eventually the build command "mvn clean package" runs by a command line and external tools (Jenkins for instance) then this code needs to be removed.

So the final Dockerfile should be like:
> FROM openjdk:8-jdk-alpine

> COPY target/*.jar app.jar

> ENTRYPOINT ["java","-jar","/app.jar"]


Once the Dockerfile is defined, run this command by the terminal:
> sudo docker build -t java-mongodb .

So the java-mongodb docker image will be created. To check you can run:
> sudo docker images

Docker compose
------------
The docker compose file allows multiple container to run and communicate each other.
On this example, a MongoDB instance will be created and linked by a Java app.

The MongoDB instance will be hosted at the port 27017 using the user/pwd root. This container will be linked by the Java docker image java-mongodb.

Run the command:
> sudo docker-compose up

So all the containers will be created and linked

## Testing the solution
To make sure the solution will work, after the command docker-compose up, type at the command line:
> sudo docker ps

The containers should be up and running:
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                      NAMES
90ce5ccbec7b        java-mongodb        "java -jar /app.jar"     3 minutes ago       Up 3 minutes        0.0.0.0:8090->8090/tcp     demo_api_1
c708bb38e5ca        mongo:latest        "docker-entrypoint.s…"   3 minutes ago       Up 3 minutes        0.0.0.0:27017->27017/tcp   mongodb

### Persist data
> curl -X POST -H 'Content-Type: application/json' -d '{"name":"marcel"}' http://localhost:8090/person

### Find data
> curl http://localhost:8090/person/all

