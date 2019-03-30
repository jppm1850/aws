FROM mysql:latest

LABEL mantainer="william"

ENV MYSQL_ALLOW_EMPTY_PASSWORD=yes
ENV MYSQL_ROOT_PASSWORD=toor
#ENV MYSQL_DATABASE=demobd
ENV MYSQL_ROOT_HOST=%

COPY mycustom.cnf /etc/mysql/conf.d/mysqld_charset.cnf
COPY ./scripts/ /docker-entrypoint-initdb.d/

#RUN cat /docker-entrypoint-initdb.d/DML.sql

EXPOSE 3306