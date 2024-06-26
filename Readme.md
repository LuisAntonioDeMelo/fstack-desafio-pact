### Desafio Técnico: Luís Antônio de Melo Gomes

Objetivo:

'Desenvolver uma aplicação web que facilite o processo de recrutamento
interno para os colaboradores da empresa. A aplicação deve permitir aos
usuários pesquisar e candidatar-se a vagas internas, passando por filtros
de requisitos específicos.'

**techs**

Para o desafio foi utilizando as versões : angular 17, Java 17 e springboot 3.3 e Postgres e Docker

**_tabelas_**

tb_requisitos

tb_analista_rh

tb_candidato

tb_candidato_vaga

tb_notificacao

tb_pessoa

tb_user

tb_vaga

tb_vaga_analista_rh

::: users jpa

\*\*

===

**Para rodar a aplicação basta apenas utilizar o docker compose**

- subira tanto o front-end o backend e banco de dados

> cmd: docker-compose-up

rodar op banco basta utilizar o docker compose

ps:

Configs:

--- caso precise rodar em outro banco

docker pull postgres

docker volume create pgdata

docker run -d --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=vaga-data1 -v pgdata:/var/lib/postgresql/data postgres

docker start postgres

version: '3' services: db: image: postgres restart: always volumes: - pgdata:/var/lib/postgresql/data environment: POSTGRES_PASSWORD: sua-senha ports: - 5432:5432 volumes: pgdata:

==
