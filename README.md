# Shoot 'Em Up Game

[![Java](https://img.shields.io/badge/Java-17%2B-orange?logo=openjdk\&logoColor=white)](https://www.java.com/)
[![OOP](https://img.shields.io/badge/Paradigm-Object--Oriented-blueviolet)](#)
[![GameLib](https://img.shields.io/badge/Library-GameLib-1f6feb)](#)
[![USP](https://img.shields.io/badge/USP-EACH-red)](https://www5.each.usp.br/)

> Implementação de um **jogo de tiro 2D estilo arcade**, desenvolvido em Java utilizando a biblioteca **GameLib** e conceitos de **Programação Orientada a Objetos**.

Projeto desenvolvido para a disciplina de **Computação Orientada a Objetos**, da Universidade de São Paulo (USP — EACH), com foco na aplicação de conceitos de orientação a objetos na construção da lógica de jogo, gerenciamento de entidades, entrada do jogador e renderização.

## Sobre o projeto

O projeto consiste em um **jogo 2D de tiro estilo arcade**, no qual o jogador controla uma nave e enfrenta diferentes tipos de inimigos e projéteis. A implementação foi estruturada de forma modular, separando a **execução do jogo**, **entrada do jogador**, **entidades**, **renderização**, **cenário** e **tipos auxiliares** em diferentes pacotes.

```text
                         ┌─────────────────┐
                         │    GameEngine   │
                         │  Loop do jogo   │
                         └────────┬────────┘
                                  │
                 ┌────────────────┼────────────────┐
                 │                │                │
                 ▼                ▼                ▼
          ┌────────────┐   ┌─────────────┐   ┌────────────┐
          │   Input    │   │  Entities   │   │  Renderer  │
          │  Handler   │   │             │   │            │
          └────────────┘   └──────┬──────┘   └────────────┘
                                  │
                    ┌─────────────┼─────────────┐
                    │             │             │
                    ▼             ▼             ▼
                 Player        Enemies      Projectiles
```

A arquitetura organiza as responsabilidades do jogo em componentes independentes, permitindo separar a lógica de execução das entidades e da representação visual.

## Arquitetura

O projeto possui cinco principais áreas de responsabilidade:

| Componente    | Responsabilidade                               |
| ------------- | ---------------------------------------------- |
| `core/`       | controle da execução, entrada e fases do jogo  |
| `entities/`   | jogador, inimigos, objetos do jogo e projéteis |
| `background/` | elementos visuais do cenário espacial          |
| `render/`     | renderização dos elementos do jogo             |
| `util/`       | tipos e estruturas auxiliares                  |

### Core

O pacote `core` concentra o funcionamento principal do jogo:

```text
core/
├── GameEngine.java
├── InputHandler.java
└── Level.java
```

O `GameEngine` coordena o ciclo de execução do jogo, enquanto o `InputHandler` processa a entrada do jogador e `Level` representa a organização das fases.

### Entities

As entidades do jogo são organizadas em classes específicas:

```text
entities/
├── GameObject.java
├── Player.java
├── EnemyGeneric.java
├── Enemy1.java
├── Enemy2.java
├── ProjectileGeneric.java
├── ProjectilePlayer.java
└── ProjectileEnemy.java
```

A estrutura permite representar diferentes elementos do jogo como objetos independentes, aplicando conceitos de **herança, encapsulamento e reutilização de comportamento**.

Entre as entidades estão:

* Jogador
* Inimigos
* Projéteis do jogador
* Projéteis dos inimigos
* Objetos genéricos do jogo

### Background

O cenário possui uma representação de campo de estrelas:

```text
background/
├── Star.java
└── StarField.java
```

Essas classes são responsáveis pelos elementos visuais utilizados para representar o fundo espacial.

### Renderização

A renderização é centralizada em:

```text
render/
└── Renderer.java
```

O componente recebe os elementos necessários e é responsável pela representação visual do estado atual do jogo.

### Utilitários

O pacote `util` contém estruturas utilizadas por diferentes componentes:

```text
util/
├── Status.java
└── Vector2D.java
```

`Vector2D` fornece uma representação para operações relacionadas a posição e movimento, enquanto `Status` representa estados utilizados pelo jogo.

## Estrutura do Repositório

```text
shoot-em-up-game/
│
├── src/
│   └── main/
│       └── java/
│           └── game/
│               ├── background/
│               │   ├── Star.java
│               │   └── StarField.java
│               │
│               ├── core/
│               │   ├── GameEngine.java
│               │   ├── InputHandler.java
│               │   └── Level.java
│               │
│               ├── entities/
│               │   ├── GameObject.java
│               │   ├── Player.java
│               │   ├── EnemyGeneric.java
│               │   ├── Enemy1.java
│               │   ├── Enemy2.java
│               │   ├── ProjectileGeneric.java
│               │   ├── ProjectilePlayer.java
│               │   └── ProjectileEnemy.java
│               │
│               ├── render/
│               │   └── Renderer.java
│               │
│               ├── util/
│               │   ├── Status.java
│               │   └── Vector2D.java
│               │
│               ├── GameLib.java
│               └── Main.java
│
├── .gitignore
└──  README.md
```

### Diretórios

| Diretório / Arquivo   | Descrição                      |
| --------------------- | ------------------------------ |
| `src/main/java/game/` | código-fonte principal         |
| `core/`               | motor do jogo, entrada e fases |
| `entities/`           | entidades e objetos do jogo    |
| `background/`         | elementos do cenário           |
| `render/`             | renderização                   |
| `util/`               | estruturas auxiliares          |
| `GameLib.java`        | biblioteca utilizada pelo jogo |
| `Main.java`           | ponto de entrada da aplicação  |

## Instalação e Uso

### Requisitos

* JDK 17 ou superior
* Java
* Windows, Linux ou macOS

### Compilação

A partir da raiz do projeto:

```bash
mkdir -p build/classes
javac -d build/classes $(find src/main/java -name "*.java")
```

No Windows PowerShell:

```powershell
New-Item -ItemType Directory -Force build/classes
$sources = Get-ChildItem src/main/java -Filter *.java -Recurse
javac -d build/classes $sources.FullName
```

### Execução

Após a compilação:

```bash
java -cp build/classes game.Main
```

O jogo será iniciado a partir da classe `game.Main`.

## Elementos do jogo

A implementação possui diferentes tipos de entidades e componentes para representar a dinâmica do jogo:

```text
                     ┌─────────────┐
                     │   Player    │
                     └──────┬──────┘
                            │
                            │ dispara
                            ▼
                  ┌──────────────────┐
                  │ ProjectilePlayer │
                  └────────┬─────────┘
                           │
                           ▼
                     ┌───────────┐
                     │  Enemy    │
                     └─────┬─────┘
                           │
                           │ dispara
                           ▼
                  ┌─────────────────┐
                  │ ProjectileEnemy │
                  └─────────────────┘
```

O jogo também possui diferentes implementações de inimigos, incluindo:

* `EnemyGeneric`
* `Enemy1`
* `Enemy2`

e diferentes tipos de projéteis:

* `ProjectileGeneric`
* `ProjectilePlayer`
* `ProjectileEnemy`

## Conceitos de Programação Orientada a Objetos

O projeto foi desenvolvido com foco na aplicação prática de conceitos de **Programação Orientada a Objetos**, incluindo:

* Classes e objetos
* Encapsulamento
* Herança
* Polimorfismo
* Composição
* Separação de responsabilidades
* Organização modular
* Abstração de entidades
* Modelagem de objetos de um sistema

A organização das entidades permite representar diferentes comportamentos de jogadores, inimigos e projéteis por meio de classes especializadas.

## Fluxo de execução

De forma simplificada, o funcionamento do jogo pode ser representado como:

```text
┌───────────────┐
│    Main       │
└───────┬───────┘
        │
        ▼
┌───────────────┐
│  GameEngine   │
└───────┬───────┘
        │
        ├───────────────┐
        │               │
        ▼               ▼
┌───────────────┐ ┌───────────────┐
│ InputHandler  │ │    Level      │
└───────┬───────┘ └───────┬───────┘
        │                 │
        └────────┬────────┘
                 ▼
        ┌─────────────────┐
        │    Entities     │
        │                 │
        │ Player / Enemy  │
        │   Projectiles   │
        └────────┬────────┘
                 │
                 ▼
        ┌─────────────────┐
        │    Renderer     │
        └────────┬────────┘
                 │
                 ▼
        ┌─────────────────┐
        │    GameLib      │
        │   Game Window   │
        └─────────────────┘
```

## Licença

Projeto Acadêmico desenvolvido para a **Universidade de São Paulo — Escola de Artes, Ciências e Humanidades (USP — EACH)**.

© 2026 Ygor Araujo

[![GitHub](https://img.shields.io/badge/GitHub-contygor-181717?logo=github\&logoColor=white)](https://github.com/contygor)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Ygor%20Araujo-0A66C2?logo=linkedin\&logoColor=white)](https://www.linkedin.com/in/contygor/)
