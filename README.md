# Projeto Spring Boot com PostgreSQL en Docker
>Este projeto é uma aplicação API REST desenvolvida com Spring Boot que gerencia usuários e tarefas. A aplicação utiliza PostgreSQL como banco de dados e é executada em um contêiner Docker.


---

## Instalação


#### Clonar o repositório

```bash
 git clone https://github.com/elysonsarmento/ToDoJava.git
```

#### Compilar o projeto

```bash
 mvn clean install
```

#### Executar o projeto

```bash
 mvn spring-boot:run
```

## Com Docker

#### Executar em Docker

```bash
 docker-compose up
```

## Endpoints de la API

### Para as tarefas

- `GET /api/tasks?search={termo}`: Busca tarefas com um termo específico.
- `POST /api/tasks`:  Cria uma nova tarefa.
- `GET /api/tasks/{id}`: Retorna uma tarefa pelo ID.
- `PUT /api/tasks/{id}`: Atualiza uma tarefa pelo ID.
- `DELETE /api/tasks/{id}`: Exclui uma tarefa pelo ID.

### Para os usuários

- `PUT /api/auth/edit`: Edita as informações de um usuário.
- `POST /api/auth/login`: Realiza o login de um usuário.
- `POST /api/auth/register`: Registra um novo usuário.

---

## Tecnologias
- Java 23
- Spring Boot
- Spring Security
- Postgresql
- Docker
- Maven
- Git
- GitHub
- IntelliJ IDEA

---
