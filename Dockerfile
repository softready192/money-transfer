FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine
VOLUME /tmp
COPY target/transfer*.jar transfer.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","/transfer.jar"]