FROM maven:3.6.3-jdk-13 as maven
MAINTAINER Hu Chengzhen <huchengzhen@gmail.com>

WORKDIR /usr/src/cohen
COPY . .
ENTRYPOINT ["mvn", "test"]