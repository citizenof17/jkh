FROM maven:3.6-jdk-8-alpine as tmp_mav

WORKDIR /tmp/app
COPY . /tmp/app
RUN mvn clean install

FROM maven:3.6-jdk-8-alpine

ENV MYSQL_CONNECTION_STRING="jdbc:mysql://localhost:3306/jkh?useSSL=false&allowPublicKeyRetrieval=true"
ENV MYSQL_DBNAME="jkh"
ENV MYSQL_USERNAME="jkh_admin"
ENV MYSQL_PASSWORD="jkh_admin"
COPY --from=tmp_mav /tmp/app/target/backend-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
