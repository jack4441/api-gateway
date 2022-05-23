FROM eclipse-temurin:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} bootcamp.apigateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/bootcamp.apigateway-0.0.1-SNAPSHOT.jar"]