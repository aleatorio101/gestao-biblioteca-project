Biblioteca de Gestão de Biblioteca
Este projeto é uma aplicação de gestão de biblioteca desenvolvida em Java utilizando Spring Boot, PostgreSQL e React. A aplicação permite o cadastro de usuários, livros, empréstimos, devoluções, recomendações de livros, e integração com a API do Google Books.

Funcionalidades
Cadastro de Usuários: CRUD completo para gerenciar usuários.
Cadastro de Livros: CRUD completo para gerenciar livros.
Empréstimos e Devoluções: Gerenciamento de empréstimos e devoluções de livros.
Recomendações de Livros: Sistema de recomendação baseado na categoria dos livros.
Integração com a API do Google Books: Busca e adição de livros a partir da API do Google Books.
Endpoints
Usuários
POST /usuarios: Criação de um novo usuário.
GET /usuarios/{id}: Busca um usuário pelo ID.
PUT /usuarios/{id}: Atualiza um usuário existente.
DELETE /usuarios/{id}: Deleta um usuário pelo ID.
GET /usuarios/test: Verifica se o endpoint está funcionando.
Livros
POST /livros: Criação de um novo livro.
GET /livros/{id}: Busca um livro pelo ID.
PUT /livros/{id}: Atualiza um livro existente.
DELETE /livros/{id}: Deleta um livro pelo ID.
GET /livros/recomendacoes/{usuarioId}: Recomenda livros para um usuário com base na categoria.
Empréstimos
POST /emprestimos: Criação de um novo empréstimo.
GET /emprestimos/{id}: Busca um empréstimo pelo ID.
PUT /emprestimos/{id}: Atualiza as informações de um empréstimo.
DELETE /emprestimos/{id}: Deleta um empréstimo pelo ID.
Integração com Google Books
GET /livros/buscar: Busca livros na API do Google Books.
Estrutura do Banco de Dados
Tabela usuarios
Coluna	Tipo	Descrição
id	BIGINT	Identificador único do usuário (Primary Key)
nome	VARCHAR	Nome do usuário
email	VARCHAR	Email do usuário
telefone	VARCHAR	Telefone do usuário
data_cadastro	TIMESTAMP	Data de cadastro do usuário
Tabela livros
Coluna	Tipo	Descrição
id	BIGINT	Identificador único do livro (Primary Key)
titulo	VARCHAR	Título do livro
autor	VARCHAR	Autor do livro
isbn	VARCHAR	Código ISBN do livro
data_publicacao	DATE	Data de publicação do livro
categoria	VARCHAR	Categoria do livro
Tabela emprestimos
Coluna	Tipo	Descrição
id	BIGINT	Identificador único do empréstimo (Primary Key)
usuario_id	BIGINT	ID do usuário que realizou o empréstimo (Foreign Key)
livro_id	BIGINT	ID do livro emprestado (Foreign Key)
data_emprestimo	TIMESTAMP	Data do empréstimo
data_devolucao	TIMESTAMP	Data de devolução prevista
status	VARCHAR	Status do empréstimo (e.g., "ativo", "concluído")
Tecnologias Utilizadas
Java 11: Linguagem de programação.
Spring Boot: Framework para criação da aplicação.
PostgreSQL: Banco de dados relacional.
Hibernate: ORM para mapeamento objeto-relacional.
JUnit 5: Framework de testes.
Mockito: Framework para criação de mocks nos testes.
React: Biblioteca JavaScript para a construção da interface do usuário.
Como Executar o Projeto
Pré-requisitos
Java 11 ou superior
Maven para gerenciamento de dependências
PostgreSQL configurado
Passos
Clone o repositório:

bash
Copiar código
git clone https://github.com/seu-usuario/nome-do-repositorio.git
Navegue até o diretório do projeto:

bash
Copiar código
cd nome-do-repositorio
Configure o banco de dados no arquivo application.properties:

properties
Copiar código
spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
spring.datasource.username=seu-usuario
spring.datasource.password=sua-senha
Execute o projeto com o Maven:

bash
Copiar código
mvn spring-boot:run
Acesse a aplicação em http://localhost:8080.
