# Projeto de Microsserviços - User API e Chamado API

Este projeto demonstra a implementação de microsserviços usando Spring Boot, separando as responsabilidades entre gerenciamento de usuários e chamados.

## Estrutura do Projeto

### Microsserviços

1. **ms-user** (Porta 8080)
   - Gerenciamento de usuários
   - Criação automática de chamados para novos usuários
   - Comunicação com ms-chamado via REST

2. **ms-chamado** (Porta 8081)
   - Gerenciamento de chamados
   - Sistema de notificações
   - Atualização de situações

## Melhorias Implementadas

### 1. Configuração Centralizada
- Arquivos `application.yml` para cada microsserviço
- Configurações específicas por ambiente
- Logging configurado

### 2. Serviço de Comunicação Robusto
- `ChamadoService` no ms-user para comunicação com ms-chamado
- Tratamento de erros e timeouts
- Logging detalhado das operações

### 3. Sistema de Notificações
- `NotificationService` no ms-chamado
- Notificações para criação e alteração de chamados
- Configurável via propriedades

### 4. Health Checks
- Endpoints `/health` em ambos os microsserviços
- Informações de status e versão

## Como Executar

### Pré-requisitos
- Java 17
- Gradle

### Executar os Microsserviços

1. **ms-user**:
```bash
cd ms-user
./gradlew bootRun
```

2. **ms-chamado**:
```bash
cd ms-chamado
./gradlew bootRun
```

## Endpoints Disponíveis

### ms-user (localhost:8080)

- `GET /health` - Health check
- `GET /api/v1/users` - Listar usuários
- `POST /api/v1/users` - Criar usuário

### ms-chamado (localhost:8081)

- `GET /health` - Health check
- `GET /api/v1/chamados` - Listar chamados
- `GET /api/v1/chamados/{id}` - Buscar chamado por ID
- `POST /api/v1/chamados` - Criar chamado
- `PATCH /api/v1/chamados/{id}/situacao` - Atualizar situação

## Exemplo de Uso

### Criar um Usuário
```bash
curl -X POST http://localhost:8080/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
    "email": "usuario@exemplo.com",
    "password": "senha123",
    "handle": "usuario",
    "name": "Nome do Usuário",
    "roles": ["ROLE_USER"],
    "company": "Empresa Exemplo"
  }'
```

Este comando irá:
1. Criar o usuário no ms-user
2. Automaticamente criar um chamado no ms-chamado
3. Gerar notificações no log

## Banco de Dados

Ambos os microsserviços usam H2 em memória:
- ms-user: `jdbc:h2:mem:userdb`
- ms-chamado: `jdbc:h2:mem:chamadodb`

Console H2 disponível em:
- http://localhost:8080/h2-console (ms-user)
- http://localhost:8081/h2-console (ms-chamado)

## Logs

Os logs detalhados estão configurados para:
- Operações de negócio
- Comunicação entre microsserviços
- Notificações de eventos

