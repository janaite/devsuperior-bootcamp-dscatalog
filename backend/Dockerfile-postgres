FROM postgres:12-alpine

ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=1234567
ENV POSTGRES_DB=dscatalog

COPY dscatalog-schema.sql /docker-entrypoint-initdb.d/00-schema.sql
COPY src/main/resources/data.sql /docker-entrypoint-initdb.d/01-data.sql
