FROM eclipse-temurin:21.0.3_9-jdk

EXPOSE 8081

WORKDIR /root

COPY ./pom.xml /root
COPY ./.mvn /root/.mvn
COPY ./mvnw /root

RUN ./mvnw dependency:go-offline

COPY ./src /root/src

RUN ./mvnw clean install

ENTRYPOINT ["java","-jar","/root/target/MicroserviceAccount-0.0.1-SNAPSHOT.jar"]