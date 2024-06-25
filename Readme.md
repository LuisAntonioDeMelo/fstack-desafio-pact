### Desafio Técnico: Luís Antônio de Melo Gomes

Objetivo:

criado sistema Dev Jobs

desenvolvido fluxo de cadastro

tanto como Admin/analista e canditado, se canditar para vaga e aparecer as notificações para os usuarios

\*\*tabelas

\*\*

**techs**

Para o desafio foi utilizando as versões : angular 17, Java 17 e springboot 3.3 e Postgres

===

subir banco

basta utilizar o docker compose

cmd: docker-compose-up

docker pull postgres

docker volume create pgdata

docker run -d --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=vaga-data1 -v pgdata:/var/lib/postgresql/data postgres

docker start postgres

version: '3' services: db: image: postgres restart: always volumes: - pgdata:/var/lib/postgresql/data environment: POSTGRES_PASSWORD: sua-senha ports: - 5432:5432 volumes: pgdata:

docker-compose up

==
