# Workers API

API REST desenvolvida em **Spring Boot** para gestão de trabalhadores (workers), departamentos e cálculo de renda mensal com base em contratos por hora.

## 📋 Descrição

O sistema permite cadastrar departamentos, trabalhadores (com nível e salário base) e contratos de horas trabalhadas. A partir desses dados, é possível consultar a renda de um trabalhador em um mês/ano específico, somando o salário base aos valores recebidos por contratos de horas realizados naquele período.

## 🚀 Tecnologias utilizadas

- **Java 25**
- **Spring Boot 4.1.0**
- **Spring Data JPA**
- **H2 Database** (banco em memória)
- **Maven**
- **Springdoc OpenAPI / Swagger UI** (documentação interativa da API)
- **JUnit 5 / MockMvc** (testes automatizados)

## 🗂️ Estrutura do domínio

- **Department**: departamento ao qual um trabalhador pertence.
- **Worker**: trabalhador, com nome, nível (`WorkerLevel`), salário base e departamento.
- **HourContract**: contrato de horas trabalhadas em uma data específica, com valor por hora e duração.

A renda mensal de um trabalhador é calculada somando seu salário base aos valores de todos os contratos de horas realizados no mês/ano informado.

## ▶️ Como rodar o projeto

### Pré-requisitos
- Java 25+
- Maven (ou use o `mvnw` incluído no projeto)

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/arthurnovaisdev/worker-management-api.git
   cd worker-management-api
   ```

2. Rode a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```

3. A aplicação sobe por padrão na porta `8080`.

## 📖 Documentação da API (Swagger)

Com a aplicação rodando, acesse:

```
http://localhost:8080/swagger-ui/index.html
```

Lá é possível visualizar e testar todos os endpoints disponíveis diretamente pelo navegador.

A especificação OpenAPI em JSON fica disponível em:
```
http://localhost:8080/v3/api-docs
```

## 🔗 Endpoints principais

### Consultar renda de um trabalhador

```
GET /workers/{id}/income/{year}/{month}
```

**Parâmetros:**
| Parâmetro | Tipo    | Descrição                          |
|-----------|---------|-------------------------------------|
| `id`      | Long    | ID do trabalhador                   |
| `year`    | Integer | Ano de referência (ex: `2018`)      |
| `month`   | Integer | Mês de referência (ex: `8`)         |

**Exemplo de requisição:**
```
GET /workers/1/income/2018/8
```

**Exemplo de resposta:**
```json
{
  "name": "Alex",
  "department": "Design",
  "income": 3000.00
}
```

## 🗄️ Banco de dados (H2 Console)

O projeto usa um banco H2 em memória para desenvolvimento e testes. Com a aplicação rodando, o console do H2 pode ser acessado em:

```
http://localhost:8080/h2-console
```

**Configuração de conexão:**
- JDBC URL: `jdbc:h2:mem:testdb`
- Usuário: `sa`
- Senha: *(em branco)*

## 🧪 Testes

O projeto conta com testes automatizados em duas camadas:

- **Testes de integração** (`@SpringBootTest` + `MockMvc`): validam o comportamento da API do ponto de vista do cliente HTTP (rotas, status codes, respostas JSON).
- **Testes de persistência** (`@DataJpaTest`): validam se entidades e relacionamentos JPA estão sendo salvos e recuperados corretamente.

Para rodar os testes:
```bash
./mvnw test
```

## 📌 Possíveis melhorias futuras

- Endpoints de CRUD completo para `Worker`, `Department` e `HourContract`
- Validação de entrada com Bean Validation (`@Valid`, `@NotNull`, etc.)
- Autenticação e autorização
- Migração para banco de dados persistente (PostgreSQL/MySQL) em produção
