FROM openjdk:8-jdk-alpine
ADD ./target/dapi.jar /workspace/app.jar
EXPOSE 8889
ENTRYPOINT ["java","-jar","/workspace/app.jar"]
