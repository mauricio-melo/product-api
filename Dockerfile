FROM openjdk:8
ENV SPRING_CLOUD_CONFIG_SERVER_GIT_DEFAULT_LABEL master

ADD build/libs/*.jar /app.jar
RUN sh -c 'chmod 777 /app.jar'

ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8

CMD java -Xms256M -Xmx512M -jar /app.jar