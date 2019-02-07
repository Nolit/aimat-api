FROM openjdk:jdk-alpine
VOLUME /tmp
RUN mkdir /app
WORKDIR /app
ENV JAVA_OPTS=""
EXPOSE 8080
COPY ./build/libs/dredear-0.0.1-SNAPSHOT.jar /app/build/libs/
ENTRYPOINT [ "sh", "-c", "java -jar -Dspring.profiles.active=docker build/libs/dredear-0.0.1-SNAPSHOT.jar" ]