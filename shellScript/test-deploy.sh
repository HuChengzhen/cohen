docker-compose stop
docker build -f DockerfileMysql -t cohen-mysql:latest .
docker run --name cohen-mysql -v ~/cohen/db-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=ASD2100150 -d cohen-mysql:latest
sleep 10
docker exec -i cohen-mysql sh -c 'exec mysql -uroot -pASD2100150' <sql/update.sql
docker stop cohen-mysql
docker container rm cohen-mysql
docker-compose -f DockerTests/docker-compose.yml build
docker-compose -f DockerTests/docker-compose.yml run cohen
docker-compose -f DockerTests/docker-compose.yml stop
docker-compose build
docker-compose up --force-recreate -d
