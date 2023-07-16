#### Start of builder image
# ------------------------
# Builder stage to prepare application for final image
FROM openjdk:11-jre as builder
WORKDIR temp

# Fatjar location, but could be set to different location from command line
ARG JAR_FILE=target/*.jar

# Copy fat jar file to current image builder
COPY ${JAR_FILE} application.jar

# Extract the jar file layers
RUN java -Djarmode=layertools -jar --enable-preview application.jar extract
#### End of builder stage

#### Start of actual image
# ------------------------
FROM openjdk:11-jre

# Set image information, but could be set to different location from command line
ARG IMAGE_VERSION="0.0.1-SNAPSHOT"
ARG IMAGE_NAME="Reservation Service"
ARG MAINTAINER="Nicholas Paterakis <nikolas.paterakis@gmail.com>"

LABEL version=${IMAGE_VERSION} name=${IMAGE_NAME} maintainer=${MAINTAINER}

# Limiting security access to not user root user
RUN addgroup nicholaspat && useradd -g nicholaspat -ms /bin/bash nicholas

# Setting user to current created user
USER nicholas

# Set working directory to application folder
WORKDIR /home/nicholas/application

# Copy all layers from builder stage to current image
COPY --from=builder temp/dependencies/ ./
COPY --from=builder temp/snapshot-dependencies/ ./
COPY --from=builder temp/spring-boot-loader/ ./
COPY --from=builder temp/application/ ./

# Expose current application to port 8083
EXPOSE 8083

# Run the application with JVM configs if any
ENTRYPOINT ["bash", "-c", \
"java -server --enable-preview \
org.springframework.boot.loader.JarLauncher ${0} ${@}"]