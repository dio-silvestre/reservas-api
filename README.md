# Reservas API

Aplicação que permita que hóspedes façam reservas em uma casa de temporada.
A aplicação possibilita o agendamento de estadias em datas específicas, com informações do hóspede e quantidade de pessoas.

<br>

## Como instalar e rodar? 🚀

Para instalar o sistema, é necessário seguir alguns passos, como baixar o projeto e fazer instalação das dependências. Para isso, é necessário abrir uma aba do terminal e digitar o seguinte:

    #Este passo é para baixar o projeto
    git clone https://github.com/dio-silvestre/reservas-api.git

Depois que terminar de baixar, abra o projeto com o IntelliJ e este cuidará das instalações de todas as dependências com o Maven.

Caso deseje rodar 

    #Entrar na pasta
    cd reservas-api

Para rodar, basta digitar o seguinte, no terminal:

    mvn spring-boot:run

E o sistema estará rodando em `http://127.0.0.1:8080`
Certifique-se de estar estar utilizando versão do Java igual ou superior à versão 20.

## Utilização 🖥️

Para utilizar este sistema, é necessário utilizar um API Client, como o [Insomnia](https://insomnia.rest/download)

<br>

## Rotas

### Descrição: CRIAR RESERVA

```
/reservas
```
#### Requisição: POST
#### Body

```json
{
    "nomeHospede": "Fulano de Tal",
    "dataInicio": "2023-08-10",
    "dataFim": "2023-08-15",
    "quantidadePessoas": 4
}
```

#### Response Sucesso

```json
{
    "id": 1,
    "nomeHospede": "Fulano de Tal",
    "dataInicio": "2023-08-10",
    "dataFim": "2023-08-15",
    "quantidadePessoas": 4,
    "status": "CONFIRMADA"
}
```

#### Response Erro

```json
{
	"timestamp": "2023-08-07T05:25:34.926253840Z",
	"status": 409,
	"error": "Data não disponível para reserva",
	"message": "Esta data já está reservada. Disponibilidade para reservas somente após o dia 15/08/2023",
	"path": "/reservas"
}
```
```json
{
	"timestamp": "2023-08-07T11:55:37.300009526Z",
	"status": 422,
	"error": "Data de entrada maior que data de saída",
	"message": "A data de entrada, 16/08/2023, não pode ser maior do que a data de saída, 15/08/2023",
	"path": "/reservas"
}
```

<br>
<br>

### Descrição: BUSCAR RESERVAS

```
/reservas
```
#### Requisição: GET
#### Response

```json
[
  {
    "id": 1,
    "nomeHospede": "Fulano de Tal",
    "dataInicio": "2023-08-10",
    "dataFim": "2023-08-15",
    "quantidadePessoas": 4,
    "status": "CONFIRMADA"
  },
  {
    "id": 2,
    "nomeHospede": "Ciclano de Tal",
    "dataInicio": "2023-09-01",
    "dataFim": "2023-09-05",
    "quantidadePessoas": 2,
    "status": "PENDENTE"
  }
]
```

<br>
<br>

### Descrição: BUSCAR RESERVA POR ID

```
/reservas/{id}
```
#### Requisição: GET
#### Response

```json
{
    "id": 1,
    "nomeHospede": "Fulano de Tal",
    "dataInicio": "2023-08-10",
    "dataFim": "2023-08-15",
    "quantidadePessoas": 4,
    "status": "CONFIRMADA"
}
```

<br>
<br>

### Descrição: ATUALIZAR UMA RESERVA

```
/reservas/{id}
```
#### Requisição: PUT
#### Body

```json
{
    "nomeHospede": "Fulano da Silva",
    "dataInicio": "2023-08-12",
    "dataFim": "2023-08-17",
    "quantidadePessoas": 5,
    "status": "PENDENTE"
}
```

#### Response

```json
{
    "id": 1,
    "nomeHospede": "Fulano da Silva",
    "dataInicio": "2023-08-12",
    "dataFim": "2023-08-17",
    "quantidadePessoas": 5,
    "status": "PENDENTE"
}
```

<br>
<br>

---

### Descrição: CANCELAR UMA RESERVA

```
/reservas/{id}/cancelar
```
#### Requisição: DELETE
#### Response

```json
{
    "id": 1,
    "nomeHospede": "Fulano da Silva",
    "dataInicio": "2023-08-12",
    "dataFim": "2023-08-17",
    "quantidadePessoas": 5,
    "status": "CANCELADA"
}
```

<br>
<br>



## Tecnologias utilizadas 📱

- Java, Spring Boot

## Licence

MIT
