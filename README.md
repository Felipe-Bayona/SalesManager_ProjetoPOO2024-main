# PROJETO POO - Sales Manager

Sales Manager é um gestor de produtos, clientes e pedidos desenvolvido com SpringData e Thymeleaf.

## Integrantes
Matheus Henrique Melo Lemes da Silva, Lucca Bellini Pena, Danielle Rachel Rios Moreira, Nicolas Luis Barbosa Bulhões e Alexandre Cavalcante da Silva

## Descrição

Este projeto tem como objetivo gerenciar produtos, clientes e pedidos dentro de bancos de dados. Utilizando o poder do SpringData para interação com o banco de dados e Thymeleaf para renderização de páginas web, Sales Manager oferece uma solução robusta e eficiente para gestão empresarial.

## Funcionalidades Principais

- **Gerenciamento de Produtos:** Adicionar, editar, remover e visualizar produtos.
- **Gerenciamento de Clientes:** Adicionar, editar, remover e visualizar clientes.
- **Gerenciamento de Pedidos:** Criar, editar, remover e visualizar pedidos.

## Diagrama de Classe

![alt text](https://raw.githubusercontent.com/CitrusMH/SalesManager_ProjetoPOO2024/main/Diagrama%20de%20Classes.png)

## Instruções de Instalação

Para instalar e rodar o projeto Sales Manager, siga os seguintes passos (Garanta que seu mysql esteja em funcionamento):

1. Clone o repositório para sua máquina local:
    ```bash
    git clone https://github.com/CitrusMH/SalesManager_ProjetoPOO2024.git
    ```
2. Navegue até o diretório do projeto:
    ```bash
    cd ProjetoPOO2024
    ```
3. Abra o projeto no IntelliJ IDEA.
4. Abra o arquivo `application.properties` localizado em `src/main/resources` e altere o usuário e a senha do banco de dados (MySQL) conforme necessário:
    ```properties
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    ```
5. Execute a aplicação a partir da IDE.

## Exemplos de Uso

### Gerenciamento de Produtos

1. Acesse a página `http://localhost:8080`.
2. Navegue pela navbar até a página de produtos.
3. Utilize os botões de ação para adicionar, editar ou remover produtos.
4. Visualize a lista completa de produtos cadastrados.

### Gerenciamento de Clientes

1. Acesse a página de clientes em `http://localhost:8080`.
2. Navegue pela navbar até a página de Clientes.
3. Utilize os botões de ação para adicionar, editar ou remover clientes.
4. Visualize a lista completa de clientes cadastrados.

### Gerenciamento de Pedidos

1. Acesse a página de pedidos em `http://localhost:8080`.
2. Navegue pela navbar até a página de pedidos.
3. Utilize os botões de ação para criar, editar ou remover pedidos.
4. Visualize a lista completa de pedidos cadastrados.

## Contribuição

Atualmente, não estamos aceitando contribuições externas.

## Licença

Este projeto não possui uma licença específica.
