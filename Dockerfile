FROM openjdk:11-jre-slim
RUN apt-get update && apt-get install -y curl
WORKDIR /app
COPY target/book-manage-1.0-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "book-manage-1.0-SNAPSHOT.jar"]
