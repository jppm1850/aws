FROM openjdk:8-jdk-alpine
LABEL maintainer="w.marchanaranda@gmail.com"
WORKDIR /workspace

#ENV userPoolId="123123"
#ENV clientId="123123"

ENV DOCKERIZE_VERSION v0.6.1
#Podemos descargar dockerize o copiarlo desde un directorio
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz

ADD target/app*.jar app.jar

ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar /workspace/app.jar
EXPOSE 8080