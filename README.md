# Product
### Esse é um serviço que tem como objetivo servir como um centralizador das operações relacionadas a produtos
<br>

#### Rodando a aplicação localmente:

1.Construa o projeto:
        ``` 
        ./gradlew clean build
        ``` 
        
2.Inicie o container da aplicação e das suas dependências utilizando o docker-compose:
        ```docker-compose up --build```
        
4.Execute ```curl -X GET http://localhost:9402/info``` 
<br>
#### Toda a documentação da API esta no Swagger da aplicação:
```http://localhost:9402/swagger-ui.html``` 

