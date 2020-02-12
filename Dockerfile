FROM openjdk:8

LABEL maintainer="subbareddygangalal@gmail.com"

VOLUME /tmp

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} employeeManagement.jar

ENTRYPOINT ["java","-jar","/employeeManagement.jar"]