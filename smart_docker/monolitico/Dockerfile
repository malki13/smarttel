FROM openjdk:8-alpine
ARG JAR_FILE=./monolitico-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /home/app.jar
EXPOSE 8099
CMD ["/bin/bash", "-c", "#(nop) ", "CMD [\"/bin/bash\"]"]
ENTRYPOINT ["java", "-jar", "/home/app.jar"]