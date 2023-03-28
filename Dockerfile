FROM openjdk:17-alpine
FROM maven:3.8.3-openjdk-17

COPY ./ ./

RUN mvn clean package

CMD ["java", "-jar", "target/MonolithJava-0.0.1-SNAPSHOT.jar"]