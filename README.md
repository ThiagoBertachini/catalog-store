### Projeto Java Spring: Catálogo de Produtos

Este projeto é uma aplicação Java Spring que oferece funcionalidades para criar, atualizar e deletar um catálogo de produtos. A aplicação utiliza MongoDB para armazenamento dos dados e integrações com AWS SNS (Simple Notification Service) e SQS (Simple Queue Service).

---

## Funcionalidades Principais

- **CRUD para Catálogo de Produtos:**
  - Criar, ler, atualizar e deletar catálogos.
  - Criar, ler, atualizar e deletar produtos em um catálogo.

- **Integração com AWS:**
  - Publicar notificações em um tópico SNS após operações CRUD.
  - Processar mensagens de uma fila SQS.
  - Salvar S3

---

## Tecnologias Utilizadas

- **Linguagem:** Java
- **Framework:** Spring Boot
- **Banco de Dados:** MongoDB
- **Integrações AWS:** SNS, SQS

---

## Requisitos

- **Java 17 ou superior**
- **Maven 3.6.3 ou superior**
- **MongoDB**
- **Conta AWS configurada com acesso a SNS e SQS**

---

## Configuração

### Configurar Variáveis de Ambiente

Defina as seguintes variáveis de ambiente para integração com AWS:

- `AWS_ACCESS_KEY_ID`
- `AWS_SECRET_ACCESS_KEY`
- `AWS_REGION`
- `AWS_SNS_TOPIC_ARN`

### Configuração do MongoDB

No arquivo `MongoDBConfig`, configure a conexão com o MongoDB:

```properties
spring.data.mongodb.uri=mongodb://<usuario>:<senha>@<host>:<porta>/<database>
