docker-compose stop
docker-compose -f DockerTests/docker-compose.yml build --no-cache
docker-compose -f DockerTests/docker-compose.yml run cohen
docker-compose -f DockerTests/docker-compose.yml stop
docker-compose build --no-cache
docker-compose up --force-recreate -d