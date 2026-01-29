# --- BUILD STAGE ---
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

# 1. Copy the wrapper and configuration files
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# 2. FIX: Give execution permission BEFORE running the dependencies command
RUN chmod +x ./gradlew

# 3. Pre-download dependencies (the "Magic" step)
RUN ./gradlew dependencies --no-daemon

# 4. Copy source code last
COPY src src

# 4. Build the JAR (will be very fast now because dependencies are cached)
RUN ./gradlew clean build -x test --no-daemon

# --- RUNTIME STAGE ---
FROM eclipse-temurin:21-jre-alpine

RUN apk add --no-cache curl
WORKDIR /app

RUN addgroup -g 1001 -S appgroup && \
    adduser -u 1001 -S appuser -G appgroup

# 5. Combined Copy + Chown (avoids the slow extraction you saw earlier)
COPY --from=build --chown=appuser:appgroup /app/build/libs/*.jar app.jar

USER appuser
EXPOSE ${PORT:-8080}

HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:${PORT:-8080}/actuator/health || exit 1

ENTRYPOINT sh -c "java -Djava.net.preferIPv4Stack=true -Dserver.port=${PORT:-8080} -jar app.jar"