# Documentação Técnica - Sistema de Tickets Microservices

## Arquitetura

### Visão Geral
Este projeto implementa uma arquitetura de microserviços para gerenciamento de tickets e usuários, desenvolvido por Jaime Silva.

### Microserviços

#### 1. User Service (ms-user)
- **Porta:** 8081
- **Responsabilidades:**
  - Gerenciamento de usuários
  - Criação de perfis
  - Gerenciamento de roles
  - Integração com Ticket Service

#### 2. Ticket Service (ms-ticket)
- **Porta:** 8082
- **Responsabilidades:**
  - Criação de tickets
  - Consulta de tickets
  - Gerenciamento de situações
  - Validações de negócio

### Comunicação entre Serviços

#### Fluxo de Criação de Usuário
1. Usuário é criado no User Service
2. User Service envia requisição assíncrona para Ticket Service
3. Ticket Service cria automaticamente um ticket para criação de email

#### Endpoints de Integração
- **User Service → Ticket Service:** `POST http://localhost:8082/api/v1/tickets`

### Tecnologias Utilizadas

#### Backend
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Spring Security**
- **H2 Database** (desenvolvimento)
- **Gradle**

#### Padrões Arquiteturais
- **Microservices Architecture**
- **REST API**
- **Repository Pattern**
- **Service Layer Pattern**
- **DTO Pattern**

### Estrutura de Dados

#### Ticket
```json
{
  "acao": "string",
  "objeto": "string", 
  "detalhamento": "string",
  "usuarioId": "integer",
  "situacao": "enum"
}
```

#### User
```json
{
  "name": "string",
  "handle": "string",
  "email": "string",
  "password": "string",
  "company": "string",
  "type": "enum",
  "roles": ["string"]
}
```

### Validações de Negócio

#### User Service
- Email deve ser válido
- Senha deve ter pelo menos 8 caracteres com letra e número
- Email deve ser único
- Handle deve ser único
- Usuário deve ter pelo menos um role

#### Ticket Service
- Ação é obrigatória
- Objeto é obrigatório
- Detalhamento é obrigatório
- Usuário ID é obrigatório

### Desenvolvedor
**Jaime Silva** - Desenvolvimento de Sistemas Distribuídos

### Data de Desenvolvimento
Dezembro 2024 