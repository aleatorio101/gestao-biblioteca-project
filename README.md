# Sistema de Gestão de Biblioteca

Este projeto é uma aplicação de gestão de biblioteca desenvolvida em Java utilizando Spring Boot, PostgreSQL e React. A aplicação permite o cadastro de usuários, livros, empréstimos, devoluções, recomendações de livros, e integração com a API do Google Books.

## Funcionalidades

- **Cadastro de Usuários:** CRUD completo para gerenciar usuários.
- **Cadastro de Livros:** CRUD completo para gerenciar livros.
- **Empréstimos e Devoluções:** Gerenciamento de empréstimos e devoluções de livros.
- **Recomendações de Livros:** Sistema de recomendação baseado na categoria dos livros.
- **Integração com a API do Google Books:** Busca e adição de livros a partir da API do Google Books.

## Endpoints

### Usuários

- `POST /criacao/usuarios`: Criação de um novo usuário.
- `GET /usuarios/{id}`: Busca um usuário pelo ID.
- `PUT /usuarios/{id}`: Atualiza um usuário existente.
- `DELETE /usuarios/{id}`: Deleta um usuário pelo ID.

### Livros

- `POST /livros`: Criação de um novo livro.
- `GET /livros/{id}`: Busca um livro pelo ID.
- `PUT /livros/{id}`: Atualiza um livro existente.
- `DELETE /livros/{id}`: Deleta um livro pelo ID.
- `GET /livros/recomendacao/{usuarioId}`: Recomenda livros para um usuário com base na categoria.
- `GET /livros/buscaPorTitulo?titulo={titulo}`: Busca um livro na API do Google Books por título e adiciona ao sistema se não existir.
- `GET /livros/buscaPorIsbn?Isbn={isbn}`: Busca um livro na API do Google Books por ISBN e adiciona ao sistema se não existir.

### Empréstimos

- `POST /emprestimos`: Criação de um novo empréstimo.
- `GET /emprestimos/{id}`: Busca um empréstimo pelo ID.
- `PUT /emprestimos/{id}`: Atualiza as informações de um empréstimo.
- `DELETE /emprestimos/{id}`: Deleta um empréstimo pelo ID.


## Estrutura do Banco de Dados

### Tabela usuarios

| Coluna         | Tipo       | Descrição                                        |
|----------------|------------|--------------------------------------------------|
| id             | BIGINT     | Identificador único do usuário (Primary Key)    |
| nome           | VARCHAR    | Nome do usuário                                 |
| email          | VARCHAR    | Email do usuário                                |
| telefone       | VARCHAR    | Telefone do usuário                             |
| data_cadastro  | TIMESTAMP  | Data de cadastro do usuário                     |

### Tabela livros

| Coluna            | Tipo       | Descrição                                    |
|-------------------|------------|----------------------------------------------|
| id                | BIGINT     | Identificador único do livro (Primary Key)  |
| titulo            | VARCHAR    | Título do livro                             |
| autor             | VARCHAR    | Autor do livro                              |
| isbn              | VARCHAR    | Código ISBN do livro                        |
| data_publicacao   | DATE       | Data de publicação do livro                 |
| categoria         | VARCHAR    | Categoria do livro                          |

### Tabela emprestimos

| Coluna             | Tipo       | Descrição                                      |
|--------------------|------------|------------------------------------------------|
| id                 | BIGINT     | Identificador único do empréstimo (Primary Key)|
| usuario_id         | BIGINT     | ID do usuário que realizou o empréstimo (Foreign Key)|
| livro_id           | BIGINT     | ID do livro emprestado (Foreign Key)          |
| data_emprestimo    | TIMESTAMP  | Data do empréstimo                            |
| data_devolucao     | TIMESTAMP  | Data de devolução prevista                    |
| status             | VARCHAR    | Status do empréstimo (e.g., "EMPRESTADO", "DEVOLVIDO")|

## Tecnologias Utilizadas

- **Java 11:** Linguagem de programação.
- **Spring Boot:** Framework para criação da aplicação.
- **PostgreSQL:** Banco de dados relacional.
- **Hibernate:** ORM para mapeamento objeto-relacional.
- **JUnit 5:** Framework de testes.
- **Mockito:** Framework para criação de mocks nos testes.
- **React:** Biblioteca JavaScript para a construção da interface do usuário.

## Como Executar o Projeto

### Pré-requisitos

- Java 11 ou superior
- Maven para gerenciamento de dependências
- PostgreSQL configurado

### Passos

1. Clone o repositório:

    ```bash
    git clone [https://github.com/aleatorio101/gestao-biblioteca-project.git]
    ```

2. Navegue até o diretório do projeto:

    ```bash
    cd gestao-biblioteca-project
    ```

3. Configure o banco de dados no arquivo `application.properties`:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
    spring.datasource.username=seu-usuario
    spring.datasource.password=sua-senha
    ```

4. Execute o projeto com o Maven:

    ```bash
    mvn spring-boot:run
    ```

5. Acesse a aplicação em [http://localhost:8080](http://localhost:8080).

   ## Documentação do Frontend

### 1. Home

**Descrição**: O componente `Home` exibe todos os livros presentes no banco de dados.

**Arquivos**:
- `src/components/Home.js`
- `src/App.css` (para estilos)

**Como Funciona**:
1. **Importações**:
   - Importa React, hooks (`useState`, `useEffect`), `axios` e o arquivo de estilos.

2. **Estado**:
   - `livros`: Armazena a lista de livros recebida da API.
   - `error`: Armazena mensagens de erro, se houver.

3. **`useEffect`**:
   - Executa a função `fetchLivros` quando o componente é montado.
   - **`fetchLivros`**:
     - Faz uma requisição GET para o endpoint `/livros`.
     - Atualiza o estado `livros` com os dados recebidos.
     - Atualiza o estado `error` em caso de falha.

4. **Renderização**:
   - Exibe um título.
   - Se `error` estiver definido, mostra a mensagem de erro.
   - Caso contrário, exibe a lista de livros ou uma mensagem de "Nenhum livro encontrado" se a lista estiver vazia.

**Como Fazer Funcionar**:
1. **Configuração do Axios**:
   - Certifique-se de que o Axios está configurado corretamente para fazer chamadas à API.
   
2. **Rota da API**:
   - Verifique se a API está rodando e a rota `/livros` está funcionando.

3. **Estilos**:
   - Certifique-se de que o arquivo `App.css` está corretamente importado e aplicado.

### 2. Navbar

**Descrição**: O componente `Navbar` fornece links de navegação para diferentes seções da aplicação.

**Arquivos**:
- `src/components/Navbar.js`

**Como Funciona**:
1. **Importações**:
   - Importa React e `Link` do `react-router-dom`.

2. **Renderização**:
   - Cria uma barra de navegação com links para as páginas principais da aplicação.

**Como Fazer Funcionar**:
1. **Configuração do React Router**:
   - Certifique-se de que o `react-router-dom` está instalado e configurado no projeto.

2. **Roteamento**:
   - Verifique se as rotas definidas correspondem às rotas esperadas na aplicação.

### 3. GoogleBooksSearch

**Descrição**: Permite buscar livros na API do Google Books e adicioná-los à biblioteca.

**Arquivos**:
- `src/components/GoogleBooksSearch.js`
- `src/App.css` (para estilos)

**Como Funciona**:
1. **Importações**:
   - Importa React, hooks (`useState`), `axios` e o arquivo de estilos.

2. **Estado**:
   - `titulo`: Armazena o título do livro a ser pesquisado.
   - `resultados`: Armazena a lista de livros retornados pela busca.

3. **`handleSearch`**:
   - Envia uma requisição GET para buscar livros com o título fornecido.
   - Atualiza o estado `resultados` com os dados recebidos.

4. **`adicionarLivro`**:
   - Envia uma requisição POST para adicionar um livro à biblioteca.

5. **Renderização**:
   - Exibe um formulário para pesquisar livros.
   - Mostra os resultados da busca com a opção de adicionar livros à biblioteca.

**Como Fazer Funcionar**:
1. **Configuração do Axios**:
   - Certifique-se de que o Axios está configurado corretamente para fazer chamadas à API.

2. **Rota da API**:
   - Verifique se as rotas para buscar e adicionar livros estão funcionando.

3. **Estilos**:
   - Certifique-se de que o arquivo `App.css` está corretamente importado e aplicado.

### 4. App

**Descrição**: Define as rotas principais da aplicação e importa os componentes.

**Arquivos**:
- `src/App.js`

**Como Funciona**:
1. **Importações**:
   - Importa React, `BrowserRouter`, `Route`, `Routes`, e os componentes principais.

2. **Renderização**:
   - Configura as rotas da aplicação usando `react-router-dom`.

**Como Fazer Funcionar**:
1. **Configuração do React Router**:
   - Certifique-se de que o `react-router-dom` está instalado e configurado no projeto.
   - Verifique se as rotas correspondem aos componentes corretos.

## Configuração e Execução do Frontend

1. Clone o repositório:

    ```bash
    git clone https://github.com/aleatorio101/gestao-biblioteca-project.git
    ```

2. Navegue até o diretório do frontend:

    ```bash
    cd gestao-biblioteca-project/biblioteca-frontend
    ```

3. Instale as dependências:

    ```bash
    npm install
    ```

4. Configure o `axios` para a URL base da API:

    - Abra o arquivo `src/axios.js` (crie o arquivo se não existir) e adicione:

      ```javascript
      import axios from 'axios';

      const instance = axios.create({
        baseURL: 'http://localhost:8080', // Altere para a URL do backend, se necessário
      });

      export default instance;
      ```

    - No seu código, substitua todas as instâncias diretas do `axios` pela instância criada:

      ```javascript
      import axios from './axios'; // Ajuste o caminho se necessário
      ```

5. Inicie o servidor de desenvolvimento:

    ```bash
    npm start
    ```

6. Acesse a aplicação no navegador:

    - Abra o navegador e vá para `http://localhost:3000`.

### Observações

- **API Backend**: Certifique-se de que o backend (Spring Boot) está rodando e acessível na URL configurada.
- **Configuração do Ambiente**: Verifique se o `baseURL` no arquivo `axios.js` corresponde à URL onde o backend está rodando.
- **Instalação de Dependências**: O comando `npm install` instala todas as dependências listadas no arquivo `package.json`. 


