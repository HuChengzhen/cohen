docker-compose stop
docker container stop cohen-mysql
docker container rm cohen-mysql
docker build -f DockerfileMysql -t cohen-mysql:latest .
docker run --name cohen-mysql -v ~/cohen/db-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=ASD2100150 -d cohen-mysql:latest
read -p "Pause Time 5 seconds" -t 5
docker exec -i cohen-mysql sh -c 'exec mysql -uroot -pASD2100150' <sql/update.sql
docker stop cohen-mysql
docker-compose build
docker-compose up --force-recreate -d
