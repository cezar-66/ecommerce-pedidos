# Desafio de E-commerce

## Objetivo

O objetivo deste projeto é construir um sistema de gerenciamento de pedidos e produtos para um e-commerce, com foco em performance, escalabilidade e segurança. As funcionalidades incluem:

- **Autenticação segura com JWT**
- **Busca eficiente de produtos com Elasticsearch**
- **Eventos assíncronos via Kafka**
- **Otimização de queries SQL para melhor performance**

## Requisitos

### Autenticação

- **JWT**: Implementação de autenticação segura com JWT.
- **Perfis de Usuário**:
    - **ADMIN**: Pode criar, atualizar e deletar produtos.
    - **USER**: Pode criar pedidos e visualizar produtos.

### Funcionalidades

- **Pedidos**:
    - O usuário pode criar um pedido com múltiplos produtos.
    - O pedido começa com o status **PENDENTE**.
    - O sistema calcula dinamicamente o valor total do pedido com base no preço atual dos produtos.
    - Criação de uma rota para realizar o pagamento do pedido.
    - Atualização do estoque do produto somente após o pagamento.
    - Se um dos produtos não tiver estoque, o pedido será automaticamente cancelado e o usuário será informado.

- **Pesquisa com Elasticsearch**:
    - Todos os produtos são indexados no Elasticsearch.
    - Endpoint de busca com filtros:
        - Nome
        - Categoria
        - Faixa de preço
    - Apenas produtos em estoque devem aparecer nos resultados da pesquisa.
    - Quando um pedido for pago, o estoque do produto é atualizado no Elasticsearch.

- **Kafka**:
    - Publicação de eventos assíncronos:
        - Quando um pedido for criado, o evento `order.created` é publicado.
        - Quando um pedido for pago, o evento `order.paid` é publicado.
    - Consumidor Kafka escutando o evento `order.paid` para atualizar o estoque do produto.

- **Queries SQL**:
    - Endpoints para consultas otimizadas no MySQL:
        - Top 5 usuários que mais compraram.
        - Ticket médio dos pedidos de cada usuário.
        - Valor total faturado no mês.

## Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot** (Security, Data, Web, Lombok)
- **MySQL**
- **Elasticsearch**
- **Kafka**

## Como Rodar

1. **Banco de Dados**: Crie um banco de dados conforme configurado em `application.properties` ou use o banco já configurado.
2. **Docker**: Utilize o Docker para rodar o Kafka e o Elasticsearch.
    - Rode o comando:
      ```bash
      docker-compose up
      ```
    - Isso vai levantar os containers necessários para o Kafka e Elasticsearch, evitando a instalação manual.
3. **Dados de Teste**: Existe uma massa de dados dentro da pasta `resources/db/migration` que deve ser executada para fins de testes.
4. **Postman**: Um arquivo de configuração do Postman está disponível na pasta `config`, contendo as rotas e exemplos de requisições para testes.

## Autor

Este projeto foi desenvolvido por **Cezar Marçal**.
