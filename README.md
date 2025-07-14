# sistema-chamados-microservices

## Implementação de Microserviços para Sistema de Chamados

### Descrição do Projeto
Este projeto implementa uma arquitetura de microserviços para gerenciamento de chamados e usuários, desenvolvido por jaime.

### Serviços Implementados

#### 1. Serviço de Chamados (ms-chamado)
- **Funcionalidades:**
  - Criar novo chamado (POST)
  - Consultar chamado por ID (GET)
  - Consultar todos os chamados (GET)
  - Alterar situação do chamado (PATCH)
  - Validações de negócio para situações resolvido/cancelado

- **Campos do Chamado:**
  - Ação (ex: instalar, configurar, criar)
  - Objeto (ex: computador, email, software)
  - Detalhamento (descrição completa da solicitação)
  - Situação (enum: NOVO, EM_ANDAMENTO, RESOLVIDO, CANCELADO)
  - Usuário (ID do solicitante)
  - Campos comuns (BaseEntity)

#### 2. Serviço de Usuários (ms-user)
- **Funcionalidades:**
  - Criar novo usuário (POST)
  - Consultar usuários (GET)
  - Consultar usuário por ID (GET)
  - Gerenciamento de perfis e roles

- **Integração Automática:**
  - Quando um usuário é criado, automaticamente abre um chamado para criação de email
  - Comunicação assíncrona entre microserviços via REST

### Tecnologias Utilizadas
- Spring Boot 3.x
- Spring Data JPA
- H2 Database (desenvolvimento)
- REST API
- Arquitetura de Microserviços

### Portas dos Serviços
- ms-user: 8081
- ms-chamado: 8082

### Desenvolvedor
jaime