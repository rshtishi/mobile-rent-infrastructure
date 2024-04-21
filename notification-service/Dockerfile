FROM azul/zulu-openjdk:21-latest
COPY target/notification-service-*.jar /app/notification-service.jar
CMD ["java", "-jar", "-Dspring.profiles.active=docker", "/app/notification-service.jar"]