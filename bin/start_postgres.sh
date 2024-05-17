#!/usr/bin/env bash

set -euo pipefail
which psql > /dev/null || (echoerr "Please ensure that postgres client is in your PATH" && exit 1)

mkdir -p $HOME/docker/volumes/postgres
rm -rf $HOME/docker/volumes/postgres/data

nerdctl run --rm --name pg-docker -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=dev -d -p 5432:5432 -v $HOME/docker/volumes/postgres:/var/lib/postgresql postgres
sleep 3
export PGPASSWORD=postgres
psql -U postgres -d dev -h localhost -f schema.sql
psql -U postgres -d dev -h localhost -f data.sql


# This file didn't work to run the postgres on docker or nerdctl.
# Please follow the steps below to run the postgres on the docker.
# 1. nerdctl pull postgres:latest/version
# 2. nerdctl run --name pg-docker -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=dev -d -p 5432:5432  postgres
# 3. nerdctl ps         ------ get the container id of the postgres instance say '6509522a3890'
# 4. nerdctl cp localPath/bin/schema.sql:6509522a3890/containerPath/schema.sql
# 5. nerdctl cp localPath/bin/data.sql:6509522a3890/containerPath/data.sql
# 6. nerdctl exec -it 6509522a3890 sh
# 7. psql -U postgres -d dev -h localhost -f schema.sql
# 8. psql -U postgres -d dev -h localhost -f data.sql
# Tables will be create and data will be added using the commands 7 and 8.
# 9. psql -U postgres -d dev
# 10. SELECT * FROM GUEST
# 11. SELECT * FROM ROOM
# To see the data in the tables run commands 9,10 and 11.
# 12 Run this project and see the output if its connected to postgres successfully. It will print the information from the tables.


