#!/bin/bash

docker rm -f lephora-db
docker run --name=lephora-db -d -p 15432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=123456 -e POSTGRES_DB=lephora postgres:14.5
