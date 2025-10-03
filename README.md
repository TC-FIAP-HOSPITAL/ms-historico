# ms-historico

Microserviço responsável por gerenciar o histórico clínico de pacientes. Ele expõe uma API GraphQL protegida por JWT, persiste dados em MySQL e integra-se a uma fila RabbitMQ para processar eventos relacionados a agendamentos.

## Sumário rápido

- [Arquitetura e responsabilidades](#arquitetura-e-responsabilidades)
- [Stack e dependências](#stack-e-dependências)
- [Configuração e variáveis](#configuração-e-variáveis)
- [Como executar localmente](#como-executar-localmente)
- [Fluxo de execução](#fluxo-de-execução)
- [API GraphQL](#api-graphql)
- [Mensageria](#mensageria)
- [Segurança](#segurança)
- [Banco de dados](#banco-de-dados)
- [Testes e qualidade](#testes-e-qualidade)
- [Estrutura de pastas](#estrutura-de-pastas)

## Arquitetura e responsabilidades

O projeto segue uma abordagem hexagonal, dividindo responsabilidades em camadas claras:

- **entrypoints**: controllers GraphQL, DTOs, mapeadores e presenters responsáveis por expor a API.
- **application**: casos de uso (`usecase`) e contratos (`gateways`) que coordenam o fluxo da aplicação.
- **domain**: regras de negócio, modelos, validações e serviços de domínio.
- **infraestrutura**: integrações externas (JPA, RabbitMQ, segurança, configuração de beans).

Principais responsabilidades:

- **HistoricoController**: declara as queries e mutations GraphQL, além de aplicar as regras de autorização por perfil (`PACIENTE`, `MEDICO`, `ENFERMEIRO`, `ADMIN`).
- **Use Cases**: encapsulam operações de busca, inserção, atualização e remoção (`BuscarHistoricoUseCase`, `InserirHistoricoUseCase`, etc.).
- **HistoricoDomainService**: garante consistência ao buscar registros existentes antes de atualizações/remoções.
- **HistoricoImpl (gateway)**: traduz domínios para entidades JPA, aplicando `HistoricoSpecification` para filtros dinâmicos.
- **SecurityConfig + JwtAuthenticationFilter**: valida JWT, carrega papeis e propaga o contexto de segurança.

## Stack e dependências

- Java 21, Spring Boot 3.5
- Spring GraphQL, Spring Web, Spring Data JPA (MySQL)
- Spring Security com JWT (`jjwt`)
- RabbitMQ (Spring AMQP)
- MapStruct para mapeamento DTO ↔ domínio
- JUnit 5, Mockito, Rest-Assured (tests)

## Configuração e variáveis

Principais propriedades (ver `src/main/resources/application.properties`):

- `server.port=8086`
- `server.servlet.context-path=/ms-historico`
- `spring.datasource.url=jdbc:mysql://localhost:3307/historico`
- `spring.datasource.username` / `spring.datasource.password`
- `spring.rabbitmq.*` para integração com RabbitMQ
- `jwt.secret` (pode ser sobrescrito via variável de ambiente `JWT_SECRET`)

Para ambientes locais existe `application-local.properties`, apontando para outro banco (`db-tc-grupo8`).

## Como executar localmente

### Pré-requisitos

- Java 21 instalado
- Maven 3.9+ (ou usar o wrapper `./mvnw`)
- Docker (opcional, mas facilita subir MySQL e RabbitMQ)

### Subindo infraestrutura com Docker

```bash
cd /Users/aricamargo/repos/FIAP
if ! docker network ls | grep -q "local-network"; then docker network create local-network; fi
docker compose up -d db-historico rabbitmq
```

### Executando o serviço

```bash
cd /Users/aricamargo/repos/FIAP/ms-historico
./mvnw spring-boot:run
```

O serviço ficará disponível em `http://localhost:8086/ms-historico`. O endpoint GraphQL padrão é `/graphql`, logo as requisições devem ir para `http://localhost:8086/ms-historico/graphql`.

### Alternativa: pacote JAR

```bash
./mvnw clean package
java -jar target/ms-historico-1.0.0.jar
```

## Fluxo de execução

1. **Autenticação**: o `JwtAuthenticationFilter` intercepta a requisição, valida o token via `JwtUtil` e popula o contexto com `MyUserDetails`.
2. **Controller GraphQL**: `HistoricoController` recebe a operação e aplica regras de autorização específicas.
3. **Use case**: coordena validações (`ValidarCamposObrigatoriosRule`) e delega ao serviço/domínio.
4. **Domínio**: `HistoricoDomainService` recupera/valida entidades existentes antes de alterações.
5. **Gateway**: `HistoricoImpl` persiste/busca dados usando `HistoricoRepository` + `HistoricoSpecification`.
6. **Resposta**: `HistoricoPresenter` converte entidades/domínios para DTOs GraphQL.

## API GraphQL

- **Endpoint**: `POST http://localhost:8086/ms-historico/graphql`
- **Playground**: se habilitado pelo `playground-spring-boot-starter`, disponível em `/playground`.

### Tipos principais (resumo)

- `Historico`: registro clínico com campos como `idPaciente`, `data`, `diagnostico`, etc.
- `HistoricoRequest`: entrada para criação.
- `HistoricoUpdateRequest`: entrada para atualização.
- `HistoricoFilter`: filtros opcionais por `idHistorico` e `idPaciente`.

### Operações

| Tipo     | Nome                 | Descrição                                                                 | Restrições de acesso                                          |
| -------- | -------------------- | ------------------------------------------------------------------------- | ------------------------------------------------------------- |
| Query    | `buscarHistoricos`   | Lista históricos por filtros opcionais. Pacientes só visualizam o próprio | `PACIENTE` (somente próprio), `MEDICO`, `ENFERMEIRO`, `ADMIN` |
| Mutation | `criarHistorico`     | Cria novo histórico com data atual.                                       | Apenas `ADMIN`                                                |
| Mutation | `atualizarHistorico` | Atualiza campos clínicos de um histórico existente.                       | `ADMIN` e `MEDICO`                                            |
| Mutation | `removerHistorico`   | Remove o registro informado.                                              | Apenas `ADMIN`                                                |

### Exemplos de uso

#### Consulta de histórico (paciente autenticado)

```graphql
query {
  buscarHistoricos(filter: { idPaciente: 123 }) {
    idHistorico
    data
    hospital
    diagnostico
    observacoes
  }
}
```

#### Criação de histórico (admin)

```graphql
mutation {
  criarHistorico(
    request: {
      idPaciente: 123
      hospital: "Hospital Central"
      medico: "Dr. Silva"
      especialidade: "Cardiologia"
      motivo: "Rotina"
      diagnostico: "OK"
      prescricao: "Retorno em 6 meses"
      observacoes: "Sem alterações"
    }
  ) {
    idHistorico
    data
  }
}
```

#### Atualização de histórico (médico ou admin)

```graphql
mutation {
  atualizarHistorico(
    idHistorico: 1
    request: {
      hospital: "Hospital Central"
      medico: "Dra. Lopes"
      especialidade: "Cardiologia"
      motivo: "Revisão"
      diagnostico: "Estável"
      prescricao: "Continuar medicação"
      observacoes: "Paciente assintomático"
    }
  ) {
    diagnostico
    observacoes
  }
}
```

## Mensageria

- Configuração no arquivo `RabbitMQConfig`.
- Exchange: `agendamento-exchange`
- Routing key: `agendamento-routingKey`
- Fila: `historico-queue`
- Consumidor: `MessageConsumerImpl` (anotado com `@RabbitListener`) atualmente apenas loga a mensagem recebida. Estenda este componente para processar eventos de agendamento e persistir novas entradas no histórico.

Para testar manualmente:

```bash
docker exec -it rabbitmq bash
# dentro do container
rabbitmqadmin publish exchange=agendamento-exchange routing_key=agendamento-routingKey payload='{"teste":true}'
```

## Segurança

- Autenticação baseada em JWT (potencialmente emitido por outro serviço). O segredo é definido em `jwt.secret`.
- `JwtAuthenticationFilter` valida o token, extrai claims `userId` e `role`.
- Perfis suportados: `ADMIN`, `MEDICO`, `ENFERMEIRO`, `PACIENTE`.
- `SecurityConfig` desabilita sessão, adiciona filtro JWT e expõe `AuthenticationEntryPoint`/`AccessDeniedHandler` customizados que retornam `ApiError` em JSON.
- O controle fino de acesso é feito em `HistoricoController`:
  - Pacientes só consultam o próprio histórico.
  - Somente administradores criam/removem registros.
  - Médicos podem atualizar registros.

## Banco de dados

Tabela `tb_historicos` (mapeada por `HistoricoEntity`):

| Campo           | Tipo              | Observação                 |
| --------------- | ----------------- | -------------------------- |
| `id_historico`  | BIGINT (PK, auto) | Identificador do histórico |
| `id_paciente`   | BIGINT            | Identificador externo      |
| `data`          | DATETIME (offset) | Populado automaticamente   |
| `hospital`      | VARCHAR           | Obrigatório                |
| `medico`        | VARCHAR           | Obrigatório                |
| `especialidade` | VARCHAR           | Obrigatório                |
| `motivo`        | VARCHAR           | Obrigatório                |
| `diagnostico`   | VARCHAR           | Obrigatório                |
| `prescricao`    | VARCHAR           | Obrigatório                |
| `observacoes`   | VARCHAR           | Obrigatório                |

As validações de obrigatoriedade são centralizadas em `ValidarCamposObrigatoriosRule` e lançam `CampoObrigatorioException` (`HTTP 400`). Quando um registro inexistente é solicitado, o serviço lança `ObjetoNaoExisteException` (`HTTP 404`).

## Testes e qualidade

### Executando testes

```bash
./mvnw test
```

Há testes unitários para os principais use cases, regras de domínio e adaptadores (ex.: `HistoricoDomainServiceImplTest`, `HistoricoImplTest`, `ValidarCamposObrigatoriosRuleTest`).

### Cobertura

O plugin JaCoCo está configurado; o relatório é gerado em `target/site/jacoco` após `mvn verify`.

## Estrutura de pastas

```
src/
  main/
    java/com/ms/historico/
      MsHistoricoApplication.java
      application/        # Casos de uso e portas
      domain/             # Regras de domínio, modelos e exceções
      entrypoints/        # Controllers GraphQL, DTOs, presenters
      infraestrutura/     # Configs, adapters JPA, segurança, mensageria
    resources/
      application.properties
      application-local.properties
      graphql/historico.graphqls
  test/                   # Suites unitárias e mocks
```

## Próximos passos sugeridos

- Implementar efetivamente o `HistoricoListenner` para tratar eventos RabbitMQ em alto nível.
- Adicionar testes de integração GraphQL e cenários de segurança.
- Documentar processo de emissão do JWT (serviço autenticador) e fornecer tokens de exemplo para ambientes de QA.
