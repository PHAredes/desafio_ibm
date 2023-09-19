# Desafio de Código: API de Organização de Jogadores em Times

Este é um projeto de desafio de código que implementa uma API para organizar jogadores em times. 
Os times são formados de acordo com a inicial do sobrenome dos jogadores, sem repetir jogadores com mesmo sobrenome no time.
A API só aceita nomes no formato { "nome" : "nome sobrenome"} inseridos um a um e é case sensitive.

---

A API oferece três endpoints:

### POST /jogador

- **Método:** POST
- **Corpo da Requisição:** `{ "nome": "Nome Sobrenome" }`
- **Retorno Esperado:** Resposta 201 Create com corpo vazio
- **Descrição:** Este endpoint permite adicionar um jogador à lista de jogadores.

### GET /times

- **Método:** GET
- **Retorno Esperado:** Resposta 200 OK com corpo contendo uma lista de times e seus jogadores.
- **Descrição:** Este endpoint retorna uma lista de times e os jogadores associados a cada time. A resposta está formatada como um objeto JSON onde cada chave representa um time identificado pelo formato "Time time_id" e o valor correspondente é uma lista de jogadores pertencentes a esse time.

### DELETE /jogador/all

- **Método:** DELETE
- **Retorno Esperado:** Resposta 204 No_Content com corpo vazio
- **Descrição:** Este endpoint remove todos os jogadores e times, reiniciando a lista de jogadores e times.

## Arquitetura em Camadas

Este projeto foi desenvolvido em Java 17 e segue uma arquitetura em camadas para manter a organização do código. As camadas incluem:

- **Controllers:** Responsável por lidar com as requisições HTTP e encaminhá-las para os serviços apropriados.
- **Facade:** Serve como uma camada intermediária entre os controllers e os services, simplificando a interação entre eles.
- **Services:** Implementam as regras de negócio da aplicação e interagem com os repositórios.
- **DTO:** Classes de Transferência de Dados que definem a estrutura dos objetos que são enviados pela API.
- **Model:** Representa os objetos de domínio da aplicação.
- **Repositories:** Responsáveis pelo acesso aos dados e interação com o banco de dados (neste caso, um banco de dados em memória).

## Requisitos

- Java 17
- Maven
- Spring Boot
- H2 Database

## Como Executar o Projeto

1. Clone este repositório para o seu ambiente de desenvolvimento.
2. Certifique-se de que você possui o Java 17 e o Maven instalados.
3. Execute o projeto utilizando o comando `mvn spring-boot:run`.
4. A API estará disponível em [http://localhost:3036](http://localhost:3036).
