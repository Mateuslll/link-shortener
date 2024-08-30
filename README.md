# Link Shortener API

Esta API é parte de um sistema de encurtamento de URLs simples, permitindo a criação, gestão e redirecionamento de URLs encurtadas.

## Tecnologias Utilizadas

- Java 11
- Spring Boot
- Spring MVC
- Spring Data MongoDB
- MongoDB
- Docker
- Postman

## Práticas Adotadas

- Princípios SOLID
- RESTful API
- Utilização de DTOs (Data Transfer Objects)
- Validação de entrada com `@Valid`
- Manipulação de exceções personalizadas

## Como Executar o Projeto

### Backend

1. **Clonar o repositório Git**

   ```bash
   git clone <URL_do_repositório>
   cd link-shortener-api
2. **Configurar o Banco de Dados MongoDB**
    
    - Certifique-se de que o MongoDB está instalado e rodando localmente ou em um contêiner Docker.
    - Caso utilize Docker, você pode rodar o MongoDB com o seguinte comando:
    
    ```bash
    docker run --name dkrcomp-mongo -d -p 27019:27017 -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=123 mongo
    ```
    
    - Configure as credenciais do MongoDB no arquivo `application.properties`:
    
    ```properties
    spring.data.mongodb.uri=mongodb://admin:123@localhost:27019/admin
    ```

3. **Compilar e Executar o Projeto**
    
    - Compile e execute o projeto com Maven:
    
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

4. **Testar Endpoints com Postman**
    
    - Utilize o Postman ou qualquer outro cliente de API para interagir com a API. Os endpoints principais estão descritos abaixo.

## Endpoints da API

### Criar uma Nova URL Encurtada

```bash
POST /api/shorten
Content-Type: application/json

{
  "originalUrl": "https://www.exemplo.com/longurl"
}
