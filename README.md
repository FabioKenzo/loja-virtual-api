# 🛒 Loja Virtual API

Esta é uma API RESTful robusta desenvolvida para o gerenciamento de uma loja virtual, focada em escalabilidade, segurança e alta disponibilidade. O projeto foi construído utilizando as tecnologias mais recentes do ecossistema Java para garantir um backend de alta performance.

## 🚀 Tecnologias Utilizadas

*   **Java 25**: Utilizando as funcionalidades mais recentes da linguagem para um código moderno e eficiente.
*   **Spring Boot 3**: Framework base para criação de microserviços e APIs rápidas.
*   **Spring Data JPA / Hibernate**: Para persistência de dados e mapeamento objeto-relacional.
*   **MySQL 8**: Banco de dados relacional para armazenamento seguro de informações.
*   **Docker & Docker Compose**: Containerização completa da aplicação e do banco de dados, facilitando o deploy e a paridade entre ambientes.
*   **Swagger (OpenAPI 3)**: Documentação interativa para teste e visualização dos endpoints da API.
*   **Maven**: Gerenciador de dependências e automação do build do projeto.

---

## 🛠️ O que foi implementado

### 🏗️ Arquitetura e Backend
*   **Tratamento Global de Exceções**: Implementação de um `ResourceExceptionHandler` para capturar erros e retornar respostas padronizadas, garantindo uma melhor experiência para quem consome a API.
*   **Segurança de Configuração**: Implementação de práticas de segurança para que credenciais sensíveis (senhas de banco de dados) nunca sejam expostas no GitHub, utilizando arquivos de exemplo e `.gitignore`.
*   **Integridade de Dados**: Foco em regras de negócio consistentes e validações de dados no backend.

### 🐳 Docker & Infraestrutura
*   **Dockerfile**: Configurado para criar imagens otimizadas utilizando o ambiente Java 25.
*   **Docker Compose**: Orquestração completa permitindo subir a API e o Banco de Dados MySQL com um único comando, garantindo que o projeto rode em qualquer máquina sem configurações manuais complexas.

### 📜 Documentação Interativa
A API conta com documentação completa via Swagger. Após rodar o projeto, você pode visualizar e testar os endpoints em:
`http://localhost:8080/swagger-ui.html`

---

## 🔧 Como Rodar o Projeto

### Pré-requisitos
*   Docker e Docker Compose instalados.
*   Git.

### Passo a Passo
1.  **Clone o repositório**:
    ```bash
    git clone [https://github.com/FabioKenzo/loja-virtual-api.git](https://github.com/FabioKenzo/loja-virtual-api.git)
    cd loja-virtual-api
