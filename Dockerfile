#define base docker image
FROM openjdk:17
LABEL maintainer="Venkatesh Selvam"
ADD target/CricketTask-0.0.1-SNAPSHOT.jar crickettask-docker.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","crickettask-docker.jar"]