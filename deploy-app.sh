#!/bin/bash

echo 'Montando o banco de dados...'
  docker-compose -f foodstore-env/docker-compose.yml up -d
echo 'Banco PostgreSQL pronto para uso'

echo 'Iniciando a aplicação'
  cd foodstore-app/
  mvn spring-boot:run &
echo 'foodstore-app em localhost:8080/foodstore-app'
