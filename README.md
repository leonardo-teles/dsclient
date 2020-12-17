# ➤ DSClient

CRUD completo de clientes como parte do trabalho final do capítulo 1 do Bootcamp DevSuperior.

### ➤ Status do Projeto

<img src="https://img.shields.io/badge/CRUD%20de%20Clientes-100%25-success"/>

### ➤ Tecnologias utilizadas
- Java
- Spring Boot
- JPA / Hibernate
- Maven


### ➤ Como rodar a aplicação

 - No terminal, clone o projeto

```bash
git clone https://github.com/leonardo-teles/dsclientg
````
- Importe para dentro do seu Eclipse/STS e/ou IDE favorita
- Execute a classe principal. O banco de dados H2 será criado automaticamente
-  Teste alguns endpoints JSON:

```bash
# http://localhost:8080/clients?page=0&linesPerPage=6&direction=ASC&orderBy=name - GET (Buscar Todas Paginado)
# http://localhost:8080/clients/1 - GET (Buscar por Id)
# http://localhost:8080/clients/1 - DELETE (Exluir Cliente)
```