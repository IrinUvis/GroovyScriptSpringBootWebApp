FROM java:8-jdk-alpine

COPY ./target/groovy-script-holder-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch demo-docker-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","groovy-script-holder-0.0.1-SNAPSHOT.jar"]