# Shoot 'Em Up Game

Projeto de jogo 2D em Java.

## Estrutura

```text
src/main/java/game/
├── background/  # Elementos do fundo
├── core/        # Loop principal, entrada e fases
├── entities/    # Jogador, inimigos e projeteis
├── render/      # Renderizacao
└── util/        # Tipos auxiliares
```

## Executar

Requer um JDK instalado. A partir da raiz do projeto:

```powershell
New-Item -ItemType Directory -Force build/classes
$sources = Get-ChildItem src/main/java -Filter *.java -Recurse
javac -d build/classes $sources.FullName
java -cp build/classes game.Main
```

Os diretorios `build/`, `out/`, arquivos `.class` e configuracoes de IDE sao locais e nao devem ser versionados.