
mvn package
docker build -t dac-atividade/app-database -f PostgresDockerfile .
docker build -t dac-atividade/apps -f ApplicationDockerfile .
docker run -p 5433:5432 --name app-db -d dac-atividade/app-database
docker run -p 8080:8080 -p 4848:4848 --link app-db:database --name apps -d dac-atividade/apps
