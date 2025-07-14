# Instruções de Execução - Sistema de Tickets Microservices

## Pré-requisitos
- Java 17 ou superior
- Gradle 7.x ou superior

## Como Executar

### 1. Executar User Service
```bash
cd ms-user
./gradlew bootRun
```
O serviço estará disponível em: http://localhost:8081

### 2. Executar Ticket Service
```bash
cd ms-ticket
./gradlew bootRun
```
O serviço estará disponível em: http://localhost:8082

## Testando a Integração

### 1. Criar um usuário
```bash
curl -X POST http://localhost:8081/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Jaime Silva",
    "handle": "jaime.silva",
    "email": "jaime.silva@empresa.com",
    "password": "senha123",
    "company": "Empresa XYZ",
    "type": "PREMIUM",
    "roles": ["ROLE_ADMIN"]
  }'
```

### 2. Verificar se o ticket foi criado automaticamente
```bash
curl http://localhost:8082/api/v1/tickets/1
```

## Endpoints Disponíveis

### User Service (8081)
- `POST /api/v1/users` - Criar usuário
- `GET /api/v1/users` - Listar usuários
- `GET /api/v1/users/{id}` - Buscar usuário por ID

### Ticket Service (8082)
- `POST /api/v1/tickets` - Criar ticket
- `GET /api/v1/tickets/{id}` - Buscar ticket por ID

## Banco de Dados
- H2 Database (memória)
- Console H2: http://localhost:8081/h2-console (User Service)
- Console H2: http://localhost:8082/h2-console (Ticket Service)

## Logs
Os logs mostrarão a comunicação entre os serviços:
- User Service: Log de criação de usuário
- Ticket Service: Log de criação de ticket

## Desenvolvedor
Jaime Silva 