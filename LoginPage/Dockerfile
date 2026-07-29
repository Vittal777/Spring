FROM openjdk:22-jdk-slim
WORKDIR /app
COPY target/LoginPage-0.0.1-SNAPSHOT.jar login.jar
EXPOSE 1725
CMD ["java","-jar","login.jar"]