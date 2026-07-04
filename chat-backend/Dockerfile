FROM maven:3.9.11-eclipse-temurin-21 AS build

WORKDIR /application

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY . .

RUN mvn -B clean install -DskipTests

FROM eclipse-temurin:21-jre-alpine

VOLUME /tmp
RUN adduser -S app
USER app

WORKDIR /application

COPY --from=build /application/target/*.jar application.jar

EXPOSE 8080

ARG SPRING_PROFILE

ENV PROFILE=$SPRING_PROFILE
ENV JVM_HEAP_DUMP_OOM_OPTION="-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp"
ENV JVM_OOM_OPTION="-XX:+CrashOnOutOfMemoryError"

ENTRYPOINT java \
    $JVM_HEAP_DUMP_OOM_OPTION \
    $JVM_OOM_OPTION \
    -jar application.jar \
    --spring.profiles.active=$PROFILE
