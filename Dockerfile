FROM maven:3.9.6-eclipse-temurin-21 AS build

ENV DISPLAY=host.docker.internal:0.0
ENV JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8"
ENV LANG=ja_JP.UTF-8
ENV LC_ALL=ja_JP.UTF-8

# Install only required libraries (NO MAVEN HERE)
# apt-get install -y wget unzip libgtk-3-0 libgbm1 libx11-6 && \
RUN apt-get update && \
    apt-get install -y wget unzip libgtk-3-0 libgbm1 libx11-6 locales fonts-noto-cjk && \
    locale-gen ja_JP.UTF-8 && \
    update-locale LANG=ja_JP.UTF-8 && \
    apt-get clean

# Download JavaFX SDK
RUN wget https://download2.gluonhq.com/openjfx/21/openjfx-21_linux-x64_bin-sdk.zip -O /tmp/openjfx.zip && \
    unzip /tmp/openjfx.zip -d /opt && \
    rm /tmp/openjfx.zip

WORKDIR /app

# Copy project
COPY pom.xml .
COPY src ./src

# ✅ NOW Maven works correctly
RUN mvn clean package -DskipTests

# Debug
RUN ls -l target/

CMD ["java", "--module-path", "/opt/javafx-sdk-21/lib", "--add-modules", "javafx.controls,javafx.fxml", "-Dfile.encoding=UTF-8", "-jar", "target/week10hw.jar"]