
## Badges
![](https://img.shields.io/badge/lang-Java-brown)

![](https://img.shields.io/badge/jdk-Oracle_Open_JDK_---->_v17.0.1-purple)

![](https://img.shields.io/badge/framework-Sring_Boot_---->_v3.1.4-darkblue)

![](https://img.shields.io/badge/deps/build-Gradle_---->_v8.2.1-def)

![](https://img.shields.io/badge/tests-JUnit_---->_v4_and_v5-orange)

![](https://img.shields.io/badge/data-PostgreSQL_---->_v16-default)
![](https://img.shields.io/badge/data-PostgreSQL_driver_---->_v42.6.0-default)


# Artigos API
Artigos API é um microsserviço que provê o gerenciamento de artigos. Pode ser utilizado no backend de aplicação de blog ou postagens - servindo como protótipo, à princípio, fazendo-se necessário adequação de acordo com a utilização.




## Features

- Lista todos os artigos;
- Lista artigo por ID procurado;
- Cria novos artigos;
- Edita dados de artigo existente;
- Deleta artigo existente por ID;
- Persiste dados em banco PostgreSQL.


## Instalando o projeto

To be defined

```bash
  {to-be-defined}
```
    
## Executando os testes

To be defined

```bash
  {to-be-defined}
```


## Utilização/Exemplos
#### Listar artigos
```
GET localhost:8080/articles
```

#### Listar artigo de id:1
```
GET localhost:8080/articles/1
```

#### Criar artigo
```
payload = {}
GET localhost:8080/articles/1
```
