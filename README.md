# 📚 Biblioteca API

API REST para gerenciamento de livros, com as quatro operações básicas de **CRUD** (Create, Read, Update, Delete), construída com **Spring Boot** e **JPA**.

> Projeto didático desenvolvido na disciplina **APIs e Microsserviços** do curso de Análise e Desenvolvimento de Sistemas (IFMS – Campus Três Lagoas), a partir do Tutorial I do Prof. Fernando Cesar Balbino.

## 🛠️ Tecnologias

| Tecnologia | Uso no projeto |
|---|---|
| Java 17+ | Linguagem |
| Spring Boot | Estrutura da aplicação |
| Spring Web | Endpoints da API REST |
| Spring Data JPA (Hibernate) | Persistência e acesso aos dados |
| H2 Database | Banco de dados leve, em modo arquivo |
| Lombok | Redução de código repetitivo (getters, setters, construtores) |
| Maven | Gerenciamento de dependências e build |

## 🧱 Arquitetura

A aplicação é dividida em camadas, cada uma com uma responsabilidade bem definida:

```
Requisição HTTP
      ↓
  Controller   → recebe a requisição e produz a resposta
      ↓
   Service     → concentra as operações da aplicação
      ↓
  Repository   → acessa os dados (Spring Data JPA)
      ↓
 Banco (H2)
```

### Estrutura de pastas

```
src/main/java/br/edu/ifms/biblioteca
├── controller
│   └── LivroController.java
├── entity
│   └── Livro.java
├── repository
│   └── LivroRepository.java
├── service
│   └── LivroService.java
└── BibliotecaApplication.java
```

## 🔗 Endpoints

| Método | Endpoint | Operação | Resposta esperada |
|---|---|---|---|
| `POST` | `/livros` | Cadastrar um livro | `201 Created` |
| `GET` | `/livros` | Listar todos os livros | `200 OK` |
| `GET` | `/livros/{id}` | Buscar um livro por ID | `200 OK` ou `404 Not Found` |
| `PUT` | `/livros/{id}` | Atualizar um livro | `200 OK` ou `404 Not Found` |
| `DELETE` | `/livros/{id}` | Excluir um livro | `204 No Content` ou `404 Not Found` |

### Exemplo de corpo da requisição (POST e PUT)

```json
{
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "anoPublicacao": 2008
}
```

### Exemplo de resposta (POST)

```json
{
  "id": 1,
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "anoPublicacao": 2008
}
```

O campo `id` é gerado automaticamente pelo banco e não deve ser enviado no cadastro.

## ▶️ Como executar

### Pré-requisitos

- JDK 17 ou superior (`java -version` para conferir)
- Não é necessário instalar o Maven: o projeto inclui o Maven Wrapper (`mvnw`)

### Passo a passo

1. Clone o repositório:
   ```bash
   git clone https://github.com/SEU-USUARIO/biblioteca-api.git
   cd biblioteca-api
   ```

2. Execute a aplicação:

   **Windows (PowerShell):**
   ```powershell
   .\mvnw spring-boot:run
   ```

   **Linux / macOS:**
   ```bash
   ./mvnw spring-boot:run
   ```

3. A API ficará disponível em `http://localhost:8080`.

> Também é possível executar pelo VS Code ou outra IDE: abra o projeto **pela pasta que contém o `pom.xml`** e rode a classe `BibliotecaApplication`.

## 🧪 Como testar

### Opção 1: REST Client (VS Code)

O arquivo [`requests.http`](requests.http) reúne as requisições prontas. Instale a extensão **REST Client** e clique em **Send Request** acima de cada bloco.

### Opção 2: PowerShell

```powershell
# Listar todos
Invoke-RestMethod http://localhost:8080/livros

# Cadastrar
Invoke-RestMethod -Method Post -Uri http://localhost:8080/livros `
  -ContentType "application/json" `
  -Body '{"titulo":"Clean Code","autor":"Robert C. Martin","anoPublicacao":2008}'
```

### Opção 3: curl (Linux / macOS)

```bash
curl -X POST http://localhost:8080/livros \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Clean Code","autor":"Robert C. Martin","anoPublicacao":2008}'
```

## 🗄️ Banco de dados (H2)

O H2 está configurado em **modo arquivo**, então os dados permanecem no disco mesmo após encerrar a aplicação. Os arquivos ficam na pasta `data/`, que está no `.gitignore`.

Com a aplicação em execução, o console web do banco fica em:

```
http://localhost:8080/h2-console
```

| Campo | Valor |
|---|---|
| JDBC URL | `jdbc:h2:file:./data/biblioteca` |
| User Name | `admin` |
| Password | *(vazia)* |

> ⚠️ A configuração `spring.jpa.hibernate.ddl-auto=update` é conveniente para desenvolvimento e estudo. Em produção, o esquema do banco costuma ser controlado por ferramentas de migração.

## 📖 O que aprendi neste projeto

- O papel de **Entity, Repository, Service e Controller** e o caminho percorrido por uma requisição
- Mapeamento objeto-relacional com **JPA/Hibernate** e a diferença entre JPA, Hibernate e Spring Data JPA
- **Injeção de dependência** pelo construtor
- Uso dos métodos prontos do `JpaRepository` (`save`, `findAll`, `findById`, `deleteById`, `existsById`)
- Trabalho com `Optional`
- Retorno dos códigos HTTP corretos: `201`, `200`, `204` e `404`
- Persistência em disco com H2

## 🚧 Próximos passos

- [ ] Validação dos dados de entrada
- [ ] Tratamento global de exceções
- [ ] Atualização parcial com `PATCH`
- [ ] Testes automatizados

## 👤 Autor

**Matheus**
Estudante de Análise e Desenvolvimento de Sistemas – IFMS, Campus Três Lagoas

[LinkedIn](https://www.linkedin.com/in/SEU-PERFIL) · [GitHub](https://github.com/SEU-USUARIO)
