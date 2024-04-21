FROM azul/zulu-openjdk:21-latest
ADD target/mobile-rent-service-*.jar /app/mobile-rent-service.jar
CMD ["java", "-jar", "-Dspring.profiles.active=docker", "/app/mobile-rent-service.jar"]