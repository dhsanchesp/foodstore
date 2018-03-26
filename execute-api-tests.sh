#!/bin/bash

echo 'Instalando newman...'
npm install newman --global;

echo 'Executando os testes...'
newman run foodstore-api-tests.postman_test_run.json
