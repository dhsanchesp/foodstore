Foodstore App
========

Aplicação demo de uma API Rest, utilizando spring-boot.

## SETUP GERAL

É necessário ter instalado no seu ambiente o maven(mvn), docker, docker-compose e node(npm). 

Para subir o banco de dados e a aplicação, execute os seguintes comandos na raiz do projeto:

```sudo chmod +x deploy-app.sh```

```./deploy-app.sh```

Este comando criará um docker container com uma imagem do postgreSQL, e iniciará a aplicação spring-boot.

### TESTES

Para executar a suite de testes rest, basta executar os comandos abaixo:

```sudo chmod +x execute-api-tests.sh```

```./execute-api-tests.sh```

Caso queira importar uma suite de testes no SoapUI, importe o arquivo **foodstore-test-soapui-project.xml**


## SETUP INDIVIDUAL DE CADA COMPONENTE E ACESSO AO BANCO DE DADOS

### BANCO DE DADOS

Abra um terminal e acesse o diretório **foodstore-env**.

Em seguida, execute o comando ```docker-compose up```

#### Acesso ao Banco de Dados

```URL=localhost:4032 DATABASE=foodstore USERNAME=admin PASSWORD=admin123```

### REST API Server

Abra um terminal e acesse o diretório **foodstore-app**.

Execute o comando ```mvn clean install```

Em seguida, execute o comando ```mvn spring-boot:run```

### Angular Client

Abra um terminal e acesse o diretório **foodstore-web**.

Execute os comandos 

```npm install```

```ng serve```

Em seguida, acesse a URL em seu browser: ```http://localhost:4200/foodstore-app```

Está disponíel um serviço de consulta de sanduiches. Basta consultar os sanduiches por código (1 a 4).

### Endpoints da Aplicação

```GET```

Consultar todos os pedidos: ```http://localhost:8080/foodstore-app/pedido/pedidos```

Consultar todos os ingredientes: ```http://localhost:8080/foodstore-app/ingrediente/ingredientes```

Consultar todos os sanduiches: ```http://localhost:8080/foodstore-app/sanduiche/sanduiches```

Consultar um ingrediente por id: ```http://localhost:8080/foodstore-app/ingrediente/1```

Consultar um sanduiche por id: ```http://localhost:8080/foodstore-app/sanduiche/1```


```POST```

Incluir um pedido: ```http://localhost:8080/foodstore-app/pedido/incluirpedido```

Calcula o valor de um sanduiche: ```http://localhost:8080/foodstore-app/pedido/calculaValorItem```

Atualiza as informações de um ingrediente: ```http://localhost:8080/foodstore-app/ingrediente/atualizar```

### Payloads de exemplo

Um payload de exemplo para a inclusao de um pedido: **payloads/incluir-pedido.json**

Um payload de exemplo para o calculo de um sanduiche: **payloads/calcula-valor-item.json**

Um payload de exemplo para atualizar um ingrediente: **payloads/atualiza-ingrediente.json**
