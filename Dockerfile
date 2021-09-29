FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine
VOLUME /tmp
COPY target/transfer*.jar money-transfer.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/money-transfer.jar"]