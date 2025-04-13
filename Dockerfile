FROM openjdk:17-jdk-alpine
EXPOSE 8089
COPY target/tp-foyer-2.0.34-SNAPSHOT.jar tp-foyer.jar
ENTRYPOINT ["java", "-jar", "/tp-foyer.jar"]
