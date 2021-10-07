#!/bin/sh

# JANAITE 20210708

docker build -t janaite/devsuperior-dscatalog-db -f Dockerfile-postgres .

docker rm -f devsup_sqlqueires

docker run -d \
   --name devsup_dscatalog \
   -p 5432:5432 \
   janaite/devsuperior-dscatalog