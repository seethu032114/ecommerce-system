# 1. Base image
FROM eclipse-temurin:17-jdk

# 2. Set working directory inside container
WORKDIR /app

#3. Copy jar from local machine to container
COPY target/inventory-service-*.jar app.jar

#4. Expose application port
EXPOSE 8080

#5. Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]