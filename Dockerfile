FROM openjdk:17-jdk-alpine
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN mkdir -p /workspace
WORKDIR /workspace
COPY ./target/dapi.jar .
EXPOSE 8080
ENTRYPOINT ["java","-Dfile.encoding=UTF-8","-jar","/workspace/dapi.jar","--spring.profiles.active=prod"]
