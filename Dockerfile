FROM fabric8/java-alpine-openjdk11-jre
#EXPOSE 8080
RUN mkdir -p /app/
ADD build/libs/test-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/app/test-0.0.1-SNAPSHOT.jar"]
