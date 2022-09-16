FROM openjdk:8-jdk-alpine
ADD ./target/dapi.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/app.jar"]
