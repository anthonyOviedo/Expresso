# Use full JDK, not JRE
FROM eclipse-temurin:23-jdk

WORKDIR /app

# Copy only the built artifact from Maven build
COPY expresso-rest/target/expresso*.jar app.jar

EXPOSE 8080

# Run it
CMD ["java", "-jar", "app.jar"]
