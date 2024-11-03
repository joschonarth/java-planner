# ✈️🌍 Plann.er - API de Gerenciamento de Viagens

API desenvolvida em Java com o framework Spring Boot para gerenciar viagens, atividades e participantes de maneira prática e organizada. A API permite o controle completo de informações sobre viagens e oferece endpoints para criar, atualizar, verificar e listar dados de viagens, atividades, participantes e links.

## ⚙️ Funcionalidades

- ✏️ **Criar Viagem**: Endpoint para criar uma nova viagem com informações básicas.
- 🔍 **Verificar Viagem**: Permite consultar os detalhes de uma viagem específica.
- 🔄 **Atualizar Viagem**: Atualiza as informações de uma viagem já existente.
- ✅ **Confirmar Viagem**: Confirma a realização de uma viagem.
- 📩 **Convidar Participante**: Adiciona um novo participante à viagem.
- 🙋‍♂️ **Confirmar Participante**: Confirma a presença de um participante na viagem.
- 👥 **Lista de Todos os Participantes**: Retorna uma lista com todos os participantes de uma viagem.
- 📅 **Criar Atividade**: Adiciona uma nova atividade para ser realizada durante a viagem.
- 📋 **Lista de Todas as Atividades**: Exibe uma lista com todas as atividades planejadas para a viagem.
- 🔗 **Criar Link Relacionado à Viagem**: Permite adicionar links relevantes à viagem, como o link de hospedagem.
- 🗒️ **Lista de Todos os Links**: Lista todos os links associados à viagem.

## 🛠️ Tecnologias Utilizadas

- ☕ **Java**: Linguagem de programação principal do projeto.
- 🌱 **Spring Boot**: Framework utilizado para a criação de APIs RESTful.
- 🗄️ **Spring Data JPA**: Para manipulação e gerenciamento de dados no banco de dados.
- 🪽 **Flyway**: Para versionamento e migrações do banco de dados.
- 💾 **H2 Database**: Banco de dados em memória para desenvolvimento e testes.
- ✨ **Lombok**: Para reduzir a verbosidade do código com anotações.
- 📦 **Maven**: Gerenciador de dependências e construção do projeto.

## ⚙️ Requisitos para Executar o Projeto

- ☕ Java 11 ou superior
- 📦 Maven 3.6+ instalado

## 🚀 Como Rodar o Projeto

📌 **1. Clone o repositório**:

```bash
git clone https://github.com/joschonarth/java-planner
```

📌 **2. Entre no diretório do projeto**:

```bash
cd java-planner
```

📌 **3. Compile e execute o projeto usando o Maven**:

```bash
mvn spring-boot:run
```

## 🌐 Acesso à API
A API estará disponível em: [http://localhost:8080](http://localhost:8080)

## 📡 Estrutura de Endpoints 

- **/trips**

    - ✏️ **POST** `/trips` - Criar uma nova viagem.
    - 🔍 **GET** `/trips/{tripId}` - Verificar uma viagem específica.
    - 🔄 **PUT** `/trips/{tripId}` - Atualizar detalhes de uma viagem.
    - ✅ **GET** `/trips/{tripId}/confirm` - Confirmar uma viagem.

- **/participants**

    - 📩 **POST** `/trips/{tripId}/invite` - Convidar um participante para a viagem.
    - 🙋‍♂️ **POST** `/participants/{participantId}/confirm` - Confirmar presença de um participante.
    - 👥 **GET** `/trips/{tripId}/participants` - Listar todos os participantes de uma viagem.

- **/trips/{tripId}/activities**

    - 📅 **POST** `/trips/{tripId}/activities` - Criar uma nova atividade para a viagem.
    - 📋 **GET** `/trips/{tripId}/activities` - Listar todas as atividades da viagem.

- **/trips/{tripId}/links**

    - 🔗 **POST** `/trips/{tripId}/links` - Adicionar um link relacionado à viagem.
    - 🗒️ **GET** `/trips/{tripId}/links` - Listar todos os links relacionados à viagem.


## 🔗 Endpoints

### ✏️ Cadastro de Viagem
- **Descrição**: Cria uma nova viagem.
- **Método**: `POST`
- **Endpoint**: `/trips`

🌐 **Exemplo de Requisição**: `http://localhost:8080/trips`

📝 **Corpo da Requisição:**

```json
{
    "destination": "Rio de Janeiro, RJ",
    "starts_at": "2024-12-01T08:00:00",
    "ends_at": "2024-12-31T08:00:00",
    "emails_to_invite": [
        "fulano@gmail.com",
        "ciclano@gmail.com"
    ],
    "owner_name": "João Otávio",
    "owner_email": "joschonarth@gmail.com"
}
```

📄 **Exemplo de Resposta:**

```json
{
    "tripId": "d8857437-0874-454a-bb31-3f08cbbb6844"
}
```

### 🔍 Verificar Viagem
- **Descrição**: Retorna os detalhes completos de uma viagem específica.
- **Método**: `GET`
- **Endpoint**: `/trips/{tripId}`

🌐 **Exemplo de Requisição**: `http://localhost:8080/trips/{tripId}`

📄 **Exemplo de Resposta:**

```json
{
    "id": "d8857437-0874-454a-bb31-3f08cbbb6844",
    "destination": "Rio de Janeiro, RJ",
    "startsAt": "2024-12-01T08:00:00",
    "endsAt": "2024-12-31T08:00:00",
    "isConfirmed": false,
    "ownerName": "João Otávio",
    "ownerEmail": "joschonarth@gmail.com"
}
```

### 🔄 Atualizar Viagem
- **Descrição**: Atualiza as informações de uma viagem existente.
- **Método**: `UPDATE`
- **Endpoint**: `/trips/{tripId}`

🌐 **Exemplo de Requisição**: `http://localhost:8080/trips/{tripId}`

📝 **Corpo da Requisição:**

```json
{
    "destination": "São Paulo, SP",
    "starts_at": "2024-12-10T08:00:00",
    "ends_at": "2024-12-31T08:00:00"
}
```

📄 **Exemplo de Resposta:**

```json
{
    "id": "d8857437-0874-454a-bb31-3f08cbbb6844",
    "destination": "São Paulo, SP",
    "startsAt": "2024-12-10T08:00:00",
    "endsAt": "2024-12-31T08:00:00",
    "isConfirmed": false,
    "ownerName": "João Otávio",
    "ownerEmail": "joschonarth@gmail.com"
}
```

### ✅ Confirmar Viagem
- **Descrição**: Confirma a realização de uma viagem específica.
- **Método**: `GET`
- **Endpoint**: `/trips/{tripId}/confirm`

🌐 **Exemplo de Requisição**: `http://localhost:8080/trips/{tripId}/confirm`

📄 **Exemplo de Resposta:**

```json
{
    "id": "d8857437-0874-454a-bb31-3f08cbbb6844",
    "destination": "São Paulo, SP",
    "startsAt": "2024-12-10T08:00:00",
    "endsAt": "2024-12-31T08:00:00",
    "isConfirmed": true,
    "ownerName": "João Otávio",
    "ownerEmail": "joschonarth@gmail.com"
}
```

### 🙋‍♂️ Confirmar Participante
- **Descrição**: Confirma a presença de um participante em uma viagem.
- **Método**: `POST`
- **Endpoint**: `/participants/{participantId}/confirm`

🌐 **Exemplo de Requisição**: `http://localhost:8080/participants/{participantId}/confirm`

📝 **Corpo da Requisição:**

```json
{
    "name": "Fulano"
}
```
📄 **Exemplo de Resposta:**

```json
{
    "id": "160a745f-907f-4117-b57c-725b2b3bee9f",
    "isConfirmed": true,
    "name": "Fulano",
    "email": "fulano@gmail.com",
    "trip": {
        "id": "d8857437-0874-454a-bb31-3f08cbbb6844",
        "destination": "São Paulo, SP",
        "startsAt": "2024-12-10T08:00:00",
        "endsAt": "2024-12-31T08:00:00",
        "isConfirmed": true,
        "ownerName": "João Otávio",
        "ownerEmail": "joschonarth@gmail.com"
    }
}
```

### 📩 Convidar Participante
- **Descrição**: Convida um novo participante para a viagem especificada.
- **Método**: `POST`
- **Endpoint**: `/trips/{tripId}/invite`

🌐 **Exemplo de Requisição**: `http://localhost:8080/trips/{tripId}/invite`

📝 **Corpo da Requisição:**

```json
{
    "email": "beltrano@gmail.com"
}
```

📄 **Exemplo de Resposta:**

```json
{
    "id": "3ab359e2-8b99-42e5-abd4-617f250fae88"
}
```

### 👥 Lista de Todos os Participantes
- **Descrição**: Retorna uma lista com todos os participantes de uma viagem específica.
- **Método**: `GET`
- **Endpoint**: `/trips/{tripId}/participants`

🌐 **Exemplo de Requisição**: `http://localhost:8080/trips/{tripId}/participants`

📄 **Exemplo de Resposta:**

```json
[
    {
        "id": "160a745f-907f-4117-b57c-725b2b3bee9f",
        "name": "Fulano",
        "email": "fulano@gmail.com",
        "isConfirmed": true
    },
    {
        "id": "b3cdece3-5cd6-44cb-a2aa-7dfeeb9ecfd6",
        "name": "Ciclano",
        "email": "ciclano@gmail.com",
        "isConfirmed": true
    },
    {
        "id": "3ab359e2-8b99-42e5-abd4-617f250fae88",
        "name": "Beltrano",
        "email": "beltrano@gmail.com",
        "isConfirmed": true
    }
]
```

### 📅 Criar Atividade
- **Descrição**: Adiciona uma atividade planejada para a viagem.
- **Método**: `POST`
- **Endpoint**: `/trips/{tripId}/activities`

🌐 **Exemplo de Requisição**: `http://localhost:8080/trips/{tripId}/activities`

📝 **Corpo da Requisição:**

```json
{
    "title": "Visita ao Cristo Redentor",
    "occurs_at": "2024-12-03T08:00:00"
}
```

📄 **Exemplo de Resposta:**

```json
{
    "activityId": "8a84eeef-9aa5-4beb-b613-bc71022c1aa1"
}
```

### 📋 Lista de Todas as Atividades
- **Descrição**: Retorna uma lista de todas as atividades planejadas para a viagem.
- **Método**: `GET`
- **Endpoint**: `/trips/{tripId}/activities`

🌐 **Exemplo de Requisição**: `http://localhost:8080/trips/{tripId}/activities`

📄 **Exemplo de Resposta:**

```json
[
    {
        "id": "8a84eeef-9aa5-4beb-b613-bc71022c1aa1",
        "title": "Visita ao Cristo Redentor",
        "occurs_at": "2024-12-03T08:00:00"
    }
]
```

### 🔗 Criar Link Relacionado à Viagem
- **Descrição**: Adiciona um link relacionado à viagem, como reservas de hospedagem ou passagens.
- **Método**: `POST`
- **Endpoint**: `/trips/{tripId}/links`

🌐 **Exemplo de Requisição**: `http://localhost:8080/trips/{tripId}/links`

📝 **Corpo da Requisição:**

```json
{
    "title": "Link do Trem do Corcovado",
    "url": "https://www.tremdocorcovado.rio/"
}
```

📄 **Exemplo de Resposta:**

```json
{
    "linkId": "832c7272-3b84-4b09-98fc-32dd2100207a"
}
```

### 🗒️ Lista de Todos os Links
- **Descrição**: Lista todos os links associados à viagem.
- **Método**: `GET`
- **Endpoint**: `/trips/{tripId}/links`

🌐 **Exemplo de Requisição**: `http://localhost:8080/trips/{tripId}/links`

📄 **Exemplo de Resposta:**

```json
[
    {
        "id": "832c7272-3b84-4b09-98fc-32dd2100207a",
        "title": "Link do Trem do Corcovado",
        "url": "https://www.tremdocorcovado.rio/"
    }
]
```

## 🤝 Contribuindo

Se você deseja contribuir com o projeto, fique à vontade para abrir uma pull request ou uma issue.

## 📞 Contato

<div>
    <a href="https://www.linkedin.com/in/joschonarth/" target="_blank"><img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>
    <a href="mailto:joschonarth@gmail.com" target="_blank"><img src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"></a>
</div>