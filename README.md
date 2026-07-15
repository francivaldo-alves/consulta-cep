# apicep

Serviço em **Spring Boot 3** que consome a API pública [ViaCEP](https://viacep.com.br/)
e persiste o resultado em um banco **H2** (em memória).

## Stack

- Java 17
- Spring Boot 4.1
- Spring Web (`RestClient` para consumir a API externa)
- Spring Data JPA + H2

## Estrutura

```
src/main/java/com/f3pro/apicep/
 ├── client/ViaCepClient.java           -> chama a API externa via RestClient
 ├── config/RestClientConfig.java       -> bean do RestClient.Builder
 ├── controller/EnderecoController.java -> endpoints REST
 ├── dto/ViaCepResponse.java            -> record que mapeia o JSON da ViaCEP (usado também na resposta)
 ├── entity/Endereco.java               -> entidade JPA persistida
 ├── exception/ApiExceptionHandler.java -> tratamento global de erros
 ├── mapper/EnderecoMapper.java         -> conversão DTO <-> Entidade
 ├── repository/EnderecoRepository.java -> Spring Data JPA
 └── service/EnderecoService.java       -> regra de negócio (valida, consulta e salva/atualiza)
```

## Como rodar

```bash
mvn spring-boot:run
```

A aplicação sobe em `http://localhost:8080`.

## Endpoint

| Método | URL                     | Descrição                                                     |
|--------|-------------------------|-----------------------------------------------------------------|
| POST   | `/enderecos/{cep}`      | Consulta a ViaCEP, salva (ou atualiza) no banco e retorna o registro |

### Exemplo

```bash
curl -X POST http://localhost:8080/enderecos/01001000
```

Resposta:
```json
{
  "cep": "01001000",
  "logradouro": "Praça da Sé",
  "complemento": "lado ímpar",
  "bairro": "Sé",
  "localidade": "São Paulo",
  "uf": "SP",
  "estado": "São Paulo",
  "regiao": "Sudeste",
  "ibge": "3550308",
  "gia": "1004",
  "ddd": "11",
  "siafi": "7107",
  "erro": false
}
```

Se o CEP não existir na base dos Correios, a API responde `404`:
```json
{
  "mensagem": "CEP não encontrado: 00000000"
}
```

Se o CEP informado não tiver 8 dígitos (ex: `"abc"` ou `"123"`), a API responde `404`
com a mensagem `"CEP inválido: <valor informado>"` (a normalização/validação acontece
antes mesmo de chamar a ViaCEP).

Se a API ViaCEP estiver fora do ar ou a chamada falhar, a API responde `502`.

## Console do H2

Acesse `http://localhost:8080/h2-console` com:
- JDBC URL: `jdbc:h2:mem:apicepdb`
- Usuário: `sa`
- Senha: (em branco)


