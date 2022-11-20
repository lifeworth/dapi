FROM openjdk:8-jdk-alpine
RUN mkdir -p /workspace
WORKDIR /workspace
COPY ./target/dapi.jar .
EXPOSE 8889
ENTRYPOINT ["java","-Dfile.encoding=UTF-8","-jar","/workspace/app.jar"]
