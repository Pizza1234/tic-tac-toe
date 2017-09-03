#!/bin/bash
# Delete all containers
#docker rm $(docker ps -a -q)
# Delete all images
#docker rmi $(docker images -q)

docker rmi tic-tac-toe
docker build -t tic-tac-toe .
docker run -ti -p 8080:8080 tic-tac-toe