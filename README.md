# Read Me First

# Auto Complete Service

The following was discovered as part of building this project:

* The original package name 'com.auto.complete.search-text' is invalid and this project uses 'com.auto.complete' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.2/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.2/maven-plugin/build-image.html)

# Steps to setup the project:

* install java 17
* maven version 3.8.1
* install docker
* for local run in intellij , in application.yaml please specify the file path on
    * input.file.location:

# Steps to build the project
* mvn clean install
* java -jar target\auto-complete-docker.jar to start the application

# Steps to build on docker

* mvn clean install
* open terminal for project root directory where DockerFile exists
    * build an image -
        * docker build -t auto-complete-image:latest .

    * Run an image

        * docker run -p 9091:8080 -d --mount type=bind,source={local_file_system_path}\BoyNames.txt,target=/data/input.txt -e INPUT_FILE_LOCATION=./data/input.txt auto-complete-image:latest

# Accessing the swagger-ui
* After application has started, open the browser and hit the below url
    * http://localhost:9091/auto-complete/swagger-ui.html

# Support
- @Adithyan Prabhakaran
- 9620348866
- prabhakaranadithyan@gmail.com