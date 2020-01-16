FROM maven:3.6.3-jdk-13 as maven
MAINTAINER Hu Chengzhen <huchengzhen@gmail.com>

WORKDIR /usr/src/cohen
COPY pom.xml .
RUN mvn dependency:resolve
COPY . .
#RUN mvn clean package
RUN mvn -Dmaven.test.skip=true package

FROM openjdk:13.0.2-jdk
MAINTAINER Hu Chengzhen <huchengzhen@gmail.com>
WORKDIR /app
COPY --from=maven /usr/src/cohen/target/cohen-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "/app/cohen-0.0.1-SNAPSHOT.jar"]