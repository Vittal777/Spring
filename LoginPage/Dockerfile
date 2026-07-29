FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/LoginPage-0.0.1-SNAPSHOT.jar login.jar
EXPOSE 1725
ENTRYPOINT ["java","-jar","login.jar"]