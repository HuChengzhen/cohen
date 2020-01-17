docker-compose stop
docker-compose -f DockerTests/docker-compose.yml build
docker-compose -f DockerTests/docker-compose.yml run cohen
docker-compose -f DockerTests/docker-compose.yml stop
docker-compose build
docker-compose up --force-recreate -d
