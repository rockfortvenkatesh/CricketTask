#define base docker image
FROM openjdk:17
LABEL maintainer="Venkatesh Selvam"
ADD target/CricketTask-0.0.1-SNAPSHOT.jar crickettask-docker.jar
ENTRYPOINT ["java","-jar","crickettask-docker.jar"]