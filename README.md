# 🚗 Consulta de Preços - Tabela FIPE

Este é um projeto Java que permite consultar preços de veículos (carros, motos e caminhões) utilizando a **API pública da Tabela FIPE**.

A aplicação é interativa via terminal e permite:
- Listar marcas disponíveis por categoria;
- Consultar os modelos da marca selecionada;
- Filtrar modelos por nome;
- Visualizar os preços médios por ano do modelo escolhido.

---

## 🛠️ Tecnologias utilizadas

- Java 17+
- API pública [Tabela FIPE](https://deividfortuna.github.io/fipe/)
- Programação Orientada a Objetos (POO)
- Java Streams
- `HttpClient` para chamadas HTTP
- Conversão JSON com biblioteca personalizada (`ConverteDados`)

---

## 📂 Estrutura de pacotes

```
br.com.oracle.g8.tabelafipe
├── principal
│   └── Principal.java         // Ponto de entrada da aplicação
├── model
│   ├── Dados.java             // Representa marca, modelo ou ano
│   ├── Modelos.java           // Representa lista de modelos + info
│   └── Veiculo.java           // Representa o veículo com valor, ano etc.
└── servico
    ├── ConsumoApi.java        // Responsável por fazer requisições HTTP
    └── ConverteDados.java     // Converte JSON em objetos Java
```

---

## ▶️ Como executar o projeto

### 1. Clone o repositório

```bash
git clone https://github.com/CruzFabio/tabelafipe-API
cd nome-do-repositorio
```

### 2. Compile o projeto

Você pode usar sua IDE favorita (IntelliJ, Eclipse etc.) ou executar via terminal:

```bash
javac -d out src/br/com/oracle/g8/tabelafipe/principal/Principal.java
```

### 3. Execute o programa

```bash
java -cp out br.com.oracle.g8.tabelafipe.principal.Principal
```

> Obs: certifique-se de que os arquivos `.java` estão organizados de acordo com os pacotes.

---

## 🧠 Conceitos aplicados

- Encapsulamento com classes modelo (`Modelos`, `Veiculo`, `Dados`)
- Separação de responsabilidades (API, conversão, lógica principal)
- Streams para ordenação e filtragem
- Manipulação de JSON
- Consumo de APIs REST com Java nativo

---

## 📸 Demonstração

```
|  -------------------------------------  |
|               TABELA FIPE               |
|  -------------------------------------  |
|         Categorias disponíveis:         |
|                                         |
| > Carros (inclui Utilitários Pequenos)  |
| > Caminhões (inclui Micro-ônibus)       |
| > Motos                                 |
|  -------------------------------------  |

> Qual deseja consultar: carros

Digite o código da marca para consulta: 21

Digite o nome do carro a ser localizado: civic

Modelos Filtrados
Código: 4828 | Nome: CIVIC 2.0 LXR 16V FLEX 4P AUTOMÁTICO
Código: 4950 | Nome: CIVIC 2.0 LXR 16V FLEXONE 4P AUTOMÁTICO

Digite o código do modelo escolhido: 4828
...
```

---

## 📌 Observações

- Este projeto é educacional e foi desenvolvido como parte do aprendizado de Java, APIs REST e boas práticas de programação.
- A API da FIPE é pública e gratuita, porém pode estar sujeita a limites de uso.

---

## 🙋‍♂️ Autor

Desenvolvido por [Fabio Cruz](https://github.com/CruzFabio)  
🚀 Projeto em aprendizado com base no curso da Oracle + Alura
