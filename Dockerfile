# FROM openjdk:17-jdk-alpine
# ARG JAR_FILE=target/auto-complete-docker.jar
# ADD ${JAR_FILE} app.jar
# ENTRYPOINT ["java","-jar","/app.jar"]

FROM openjdk:17-jdk-alpine as builder
ARG JAR_FILE=target/auto-complete-docker.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract
ENV INPUT_FILE_LOCATION ./data/input.txt

FROM openjdk:17-jdk-alpine
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]