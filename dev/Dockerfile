FROM maven:3.6-jdk-8-alpine as tmp_mav

WORKDIR /tmp/app
COPY . /tmp/app
RUN mvn clean install

FROM maven:3.6-jdk-8-alpine

ENV MYSQL_CONNECTION_STRING="jdbc:mysql://localhost:3306/jkh?useSSL=false&allowPublicKeyRetrieval=true"
ENV MYSQL_DBNAME="jkh"
ENV MYSQL_USERNAME="jkh_admin"
ENV MYSQL_PASSWORD="jkh_admin"
ENV DEFAULT_PERIOD_BETWEEN_COUNTERS_SENDINGS=30
ENV ADMIN_LOGIN="Admin"
ENV ADMIN_PASSWORD="Admin_123"
ENV ADMIN_NAME="Фамилия Имя Отчество"
ENV ADMIN_EMAIL="administrator_jkh@mail.ru"
ENV ADMIN_PHONE="+78005553535"
COPY --from=tmp_mav /tmp/app/target/backend-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
