## 📄 `README.md` — Projeto XuBank

# 🏦 XuBank API

API RESTful para gerenciamento de contas bancárias de uma fintech fictícia, desenvolvida com Spring Boot no padrão MVC, com suporte a múltiplos tipos de contas, operações e relatórios gerenciais.


## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.2.5
- Spring Data JPA
- H2 Database (memória)
- Swagger OpenAPI (Springdoc)
- JUnit 5 (testes unitários)
- Maven


## 📂 Estrutura do Projeto



src/
└── main/
├── java/com/xubank/
│   ├── controller/       → Endpoints REST
│   ├── dto/              → DTOs para entrada de dados
│   ├── model/            → Entidades JPA
│   ├── repository/       → Interfaces JPA
│   └── service/          → Lógica de negócio
└── resources/
└── application.properties


## ⚙️ Como Executar o Projeto

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/xubank.git
cd xubank
````

### 2. Compile e execute com Maven

```bash
./mvnw spring-boot:run
```



## 🧪 Acessando o Swagger (Documentação e Testes)

Após subir o servidor:

📚 Documentação interativa:


http://localhost:8080/swagger-ui.html


📄 API Docs (JSON):

http://localhost:8080/api-docs



## 🧮 Exemplos de Endpoints

### Clientes

* `POST /clientes` → Cadastrar cliente
* `GET /clientes/{cpf}` → Buscar cliente

### Contas

* `POST /clientes/{cpf}/contas` → Criar conta (com tipo)
* `POST /contas/{id}/deposito?valor=100`
* `POST /contas/{id}/saque?valor=50`
* `GET /contas/{id}/extrato`

### Direção

* `GET /direcao/saldos` → Total por tipo de conta
* `GET /direcao/saldo-medio`
* `GET /direcao/clientes-extremos`


## 🧪 Testes

Execute os testes com:

```bash
./mvnw test
```

Os testes cobrem:

* Depósitos e saques
* Regras de rendimento de cada tipo de conta
* Validações de erro


## 🧑‍🏫 Informações Acadêmicas

**Curso**: Sistemas de Informação
**Disciplina**: Programação Modular
**Instituição**: PUC Minas
**Professor**: Daniel Kansaon
**Alunos**: \[Seu Nome] e \[Nome do colega, se em dupla]



## ✅ Status do Projeto

✅ Cadastro de clientes
✅ Criação de contas com regras específicas
✅ Operações de saque e depósito
✅ Relatórios para diretoria
✅ Testes unitários
✅ Swagger para testes e documentação



## 📘 Licença

Este projeto é apenas para fins educacionais.




