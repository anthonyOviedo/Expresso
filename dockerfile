# ---------- Build stage ----------
FROM eclipse-temurin:23-jdk AS build

WORKDIR /src
# Copy source
COPY HelloWorld.java .

# Compile and package into a runnable JAR with Main-Class set
RUN javac HelloWorld.java \
 && jar --create --file app.jar --main-class=HelloWorld HelloWorld.class

# ---------- Runtime stage ----------
FROM eclipse-temurin:23-jre AS run

WORKDIR /app
# Bring only the built artifact
COPY --from=build /src/app.jar ./app.jar

# Run it
CMD ["java", "-jar", "app.jar"] 
