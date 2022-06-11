FROM openjdk:11.0.12
EXPOSE 8080
RUN mkdir -p /app/
ADD build/libs/deck-0.0.1-SNAPSHOT.jar /app/deck-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/deck-0.0.1-SNAPSHOT.jar"]