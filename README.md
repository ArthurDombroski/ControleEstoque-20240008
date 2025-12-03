# API de Controle de Estoque

Uma API REST desenvolvida com **Spring Boot 3.5.8** para gerenciamento completo de estoque, incluindo categorias de produtos, fornecedores, clientes, vendedores e vendas.

## 📋 Índice

- [Visão Geral](#visão-geral)
- [Pré-requisitos](#pré-requisitos)
- [Instalação e Setup](#instalação-e-setup)
- [Executando a Aplicação](#executando-a-aplicação)
- [Testando a API](#testando-a-api)
- [Endpoints Disponíveis](#endpoints-disponíveis)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)

## 🎯 Visão Geral

Esta API fornece funcionalidades completas para:

- **Categorias**: Criar e gerenciar categorias de produtos
- **Produtos**: Gerenciar produtos com preço e estoque
- **Fornecedores**: Registrar e manter dados de fornecedores
- **Clientes**: Manter cadastro de clientes
- **Vendedores**: Gerenciar vendedores
- **Vendas**: Registrar e controlar vendas com itens e preço total
- **Estoque**: Controle automático de estoque por produto

## 🔧 Pré-requisitos

Antes de inicializar o projeto, certifique-se de que você tem os seguintes itens instalados:

### Requisitos Obrigatórios

- **Java 21 ou superior** - [Download JDK 21](https://www.oracle.com/java/technologies/downloads/#java21)
- **Maven 3.6+** - O projeto inclui `mvnw` (Maven Wrapper) para facilitar
- **Git** - Para versionamento

### Verificar Instalação

```bash

java -version


mvn -version



## Instalação e Setup

### 1. Clonar o Repositório

```bash
git clone https://github.com/ArthurDombroski/ControleEstoque-20240008.git
cd ControleEstoque-20240008
```

### 2. Configurar Banco de Dados MySQL

#### a) Configurar Credenciais (application.properties)

Edite o arquivo `src/main/resources/application.properties`:

```properties
# Configuração da aplicação
spring.application.name=api-estoque

# Configuração do banco de dados MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/controle_estoque
spring.datasource.username=root
spring.datasource.password=sua_senha_aqui
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuração JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Porta padrão
server.port=8080
```

**Notas importantes:**
- Substitua `sua_senha_aqui` pela sua senha de root do MySQL
- `ddl-auto=update` cria/atualiza tabelas automaticamente
- `ddl-auto=validate` para validar apenas (recomendado em produção)

### 3. Instalar Dependências

```bash
# Usando Maven Wrapper (Windows)
mvnw clean install

# Ou usando Maven instalado
mvn clean install
```

## 🚀 Executando a Aplicação

### Opção 1: Usando Maven Wrapper (Windows)

```bash
mvnw spring-boot:run
```

### Opção 2: Usando Maven Instalado

```bash
mvn spring-boot:run
```

### Opção 3: Construir JAR e Executar

```bash
# Construir
mvnw clean package

# Executar
java -jar target/api-estoque-0.0.1-SNAPSHOT.jar
```

A aplicação será iniciada em: **http://localhost:8080**

## 🧪 Testando a API

### 1. Usando Postman ou Insomnia

Importe as requisições abaixo ou crie uma coleção de testes.

### 2. Usando cURL

#### Exemplo 1: Criar Categoria

```bash
curl -X POST http://localhost:8080/api/categorias \
  -H "Content-Type: application/json" \
  -d '{"nome":"Eletrônicos"}'
```

#### Exemplo 2: Listar Todas as Categorias

```bash
curl http://localhost:8080/api/categorias
```

### 3. Executar Testes Unitários

```bash
# Executar todos os testes
mvnw test

# Ou com Maven
mvn test
```

## 📡 Endpoints Disponíveis

### Categorias

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/categorias` | Listar todas as categorias |
| `GET` | `/api/categorias/{id}` | Obter categoria por ID |
| `POST` | `/api/categorias` | Criar nova categoria |
| `PUT` | `/api/categorias/{id}` | Atualizar categoria |
| `DELETE` | `/api/categorias/{id}` | Deletar categoria |


### Clientes

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/clientes` | Listar todos os clientes |
| `GET` | `/api/clientes/{id}` | Obter cliente por ID |
| `POST` | `/api/clientes` | Criar novo cliente |
| `PUT` | `/api/clientes/{id}` | Atualizar cliente |
| `DELETE` | `/api/clientes/{id}` | Deletar cliente |

### Produtos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/produtos` | Listar todos os produtos |
| `GET` | `/api/produtos/{id}` | Obter produto por ID |
| `POST` | `/api/produtos` | Criar novo produto |
| `PUT` | `/api/produtos/{id}` | Atualizar produto |
| `DELETE` | `/api/produtos/{id}` | Deletar produto |

### Fornecedores

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/fornecedores` | Listar todos os fornecedores |
| `GET` | `/api/fornecedores/{id}` | Obter fornecedor por ID |
| `POST` | `/api/fornecedores` | Criar novo fornecedor |
| `PUT` | `/api/fornecedores/{id}` | Atualizar fornecedor |
| `DELETE` | `/api/fornecedores/{id}` | Deletar fornecedor |

### Vendedores

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/vendedores` | Listar todos os vendedores |
| `GET` | `/api/vendedores/{id}` | Obter vendedor por ID |
| `POST` | `/api/vendedores` | Criar novo vendedor |
| `PUT` | `/api/vendedores/{id}` | Atualizar vendedor |
| `DELETE` | `/api/vendedores/{id}` | Deletar vendedor |

### Vendas

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/vendas` | Listar todas as vendas |
| `GET` | `/api/vendas/{id}` | Obter venda por ID |
| `POST` | `/api/vendas` | Registrar nova venda |
| `DELETE` | `/api/vendas/{id}` | Deletar venda |



## 📁 Estrutura do Projeto

```
ControleEstoque-20240008/
├── src/
│   ├── main/
│   │   ├── java/com/controleestoque/api_estoque/
│   │   │   ├── ApiEstoqueApplication.java      
│   │   │   ├── controller/                      
│   │   │   │   ├── CategoriaController.java
│   │   │   │   ├── ClienteController.java
│   │   │   │   ├── ProdutoController.java
│   │   │   │   ├── FornecedorController.java
│   │   │   │   ├── VendedorController.java
│   │   │   │   └── VendaController.java
│   │   │   ├── model/                           
│   │   │   │   ├── Categoria.java
│   │   │   │   ├── Produto.java
│   │   │   │   ├── Cliente.java
│   │   │   │   ├── Fornecedor.java
│   │   │   │   ├── Vendedor.java
│   │   │   │   ├── Venda.java
│   │   │   │   ├── ItemVenda.java
│   │   │   │   └── Estoque.java
│   │   │   ├── repository/                      
│   │   │   │   ├── CategoriaRepository.java
│   │   │   │   ├── ProdutoRepository.java
│   │   │   │   ├── ClienteRepository.java
│   │   │   │   ├── FornecedorRepository.java
│   │   │   │   ├── VendedorRepository.java
│   │   │   │   ├── VendaRepository.java
│   │   │   │   └── ItemVendaRepository.java
│   │   │   ├── service/                         
│   │   │   │   └── VendaService.java
│   │   │   └── dto/                             
│   │   │       ├── VendaRequestDTO.java
│   │   │       └── ItemVendaRequestDTO.java
│   │   └── resources/
│   │       └── application.properties           
│   └── test/
│       └── java/com/controleestoque/api_estoque/
│           └── ApiEstoqueApplicationTests.java
├── pom.xml                                      
├── mvnw                                         
├── mvnw.cmd                                     
└── README.md                                    
```

## 🛠️ Tecnologias Utilizadas

### Framework & Linguagem
- **Java 21** - Linguagem de programação
- **Spring Boot 3.5.8** - Framework web
- **Spring Data JPA** - ORM e acesso a dados


### Ferramentas & Dependências
- **Maven 3.6+** - Gerenciador de dependências
- **Lombok** - Redução de boilerplate
- **Spring Boot Test** - Framework de testes

