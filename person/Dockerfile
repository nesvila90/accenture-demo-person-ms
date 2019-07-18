FROM openjdk:8-jdk-alpine
MAINTAINER nestorvl2011@hotmail.com
EXPOSE 8080
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]