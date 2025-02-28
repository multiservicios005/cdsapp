#FROM maven:3-jdk-8 AS builder
FROM maven:3.8.3-openjdk-17 AS builder
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src src
RUN mvn -e -B package


#FROM openjdk:8-jdk-alpine
FROM openjdk:17-alpine
EXPOSE 8080
#COPY usuarios.json .
COPY --from=builder /target/cdsapp-1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]