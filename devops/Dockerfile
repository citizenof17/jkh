FROM mysql
ENV MYSQL_DATABASE=jkh
ENV MYSQL_ALLOW_EMPTY_PASSWORD=1
COPY ./sql-scripts/ /docker-entrypoint-initdb.d/
