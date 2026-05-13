# 🛒 Loja Virtual API

Esta é uma API RESTful robusta desenvolvida para o gerenciamento de uma loja virtual, focada em escalabilidade, segurança e alta disponibilidade. O projeto foi construído utilizando as tecnologias mais recentes do ecossistema Java para garantir um backend de alta performance.

## 🚀 Tecnologias Utilizadas

* **Java 25**: Utilizando as funcionalidades mais recentes da linguagem para um código moderno e eficiente.
* **Spring Boot 3**: Framework base para criação de microserviços e APIs rápidas.
* **Spring Data JPA / Hibernate**: Para persistência de dados e mapeamento objeto-relacional.
* **MySQL 8**: Banco de dados relacional para armazenamento seguro de informações.
* **Docker & Docker Compose**: Containerização completa da aplicação e do banco de dados, facilitando o deploy.
* **Swagger (OpenAPI 3)**: Documentação interativa para teste e visualização dos endpoints da API.
* **Maven**: Gerenciador de dependências e automação do build.

---

## 🛠️ O que foi implementado

### 🏗️ Classes Principais e Validações
* **Modelagem de Entidades (POO)**: Classes estruturadas com foco em Programação Orientada a Objetos, garantindo que atributos como nome, preço e estoque sigam regras de negócio rigorosas.
* **Tratamento Global de Exceções**: Implementação de um `ResourceExceptionHandler` e classes de erro personalizadas para garantir que a API retorne respostas claras e padronizadas em caso de falhas.
* **Validações de Dados**: Camada de lógica para assegurar a integridade e consistência dos dados persistidos no MySQL.

### 🐳 Docker & Infraestrutura
* **Dockerfile**: Configurado para criar imagens otimizadas utilizando o ambiente Java 25.
* **Docker Compose**: Orquestração completa permitindo subir a API e o Banco de Dados com um único comando, garantindo portabilidade absoluta.

### 🛡️ Segurança e Boas Práticas
* **Segurança de Credenciais**: Implementação de `.gitignore` e arquivos de exemplo (`application.properties.example`) para garantir que senhas e dados sensíveis nunca sejam expostos no controle de versão.

---

## 📜 Documentação Interativa
A API conta com documentação completa via Swagger. Após rodar o projeto, você pode visualizar e testar os endpoints em:
`http://localhost:8080/swagger-ui.html`

---

## 🔧 Como Rodar o Projeto

1. **Clone o repositório**:
    ```bash
    git clone https://github.com/FabioKenzo/loja-virtual-api.git
    ```

2. **Suba os containers com Docker Compose**:
    ```bash
    docker-compose up -d
    ```

---

## 📈 Intuito Acadêmico

Este projeto foi desenvolvido por **Fabio Kenzo Okamura** como parte de um plano estratégico de aprofundamento técnico em:
* **Java (POO)** e padrões de projeto.
* **Spring Boot** para APIs escaláveis.
* **MySQL** para gerenciamento de dados relacionais.
* **Swagger** para documentação profissional de software.
* **Docker** para automação de infraestrutura e cultura DevOps.

---

## 👤 Autor

**Fábio Kenzo Okamura**
* Graduando em Análise e Desenvolvimento de Sistemas (ADS) - **UNITAU**.
* Foco em Desenvolvimento Backend, Java e DevOps.

