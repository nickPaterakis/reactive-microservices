FROM openjdk:11-jre as builder
WORKDIR application
ARG ARTIFACT_NAME
COPY ${ARTIFACT_NAME}.jar application.jar
RUN java -Djarmode=layertools -jar application.jar extract

# Download dockerize and cache that layer
ARG DOCKERIZE_VERSION
RUN wget -O dockerize.tar.gz https://github.com/jwilder/dockerize/releases/download/${DOCKERIZE_VERSION}/dockerize-alpine-linux-amd64-${DOCKERIZE_VERSION}.tar.gz
RUN tar xzf dockerize.tar.gz
RUN chmod +x dockerize


# wget is not installed on adoptopenjdk:11-jre-hotspot
FROM adoptopenjdk:11-jre-hotspot

# Set image information, but could be set to different location from command line
ARG IMAGE_VERSION="0.0.1-SNAPSHOT"
ARG IMAGE_NAME="Config Server"
ARG MAINTAINER="Nicholas Paterakis <nikolas.paterakis@gmail.com>"

LABEL version=${IMAGE_VERSION} name=${IMAGE_NAME} maintainer=${MAINTAINER}

# Limiting security access to not user root user
RUN addgroup nicholaspat && useradd -g nicholaspat -ms /bin/bash nicholas

# Setting user to current created user
USER nicholas

# Set working directory to application folder
WORKDIR /home/nicholas/application

# Dockerize
COPY --from=builder application/dockerize ./

ARG EXPOSED_PORT
EXPOSE ${EXPOSED_PORT}

COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
