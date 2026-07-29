FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/LoginPage-0.0.1-SNAPSHOT.jar login.jar
EXPOSE 1725
ENTRYPOINT ["java","-jar","login.jar"]