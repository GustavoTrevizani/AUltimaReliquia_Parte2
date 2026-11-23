# A Última Relíquia — Uma aventura esquecida

Jogo 2D de aventura desenvolvido em **Java** para o Trabalhos 1 e 2 da disciplina de **Programação II**.

O jogador deve explorar diferentes regiões, coletar chaves, enfrentar inimigos e, ao final, derrotar o Guardião para recuperar a Última Relíquia.

## 🎮 Sobre o jogo

**A Última Relíquia** é um jogo 2D de aventura com temática medieval/fantasia.

A jornada é dividida em três fases:

1. **Costa / Floresta** — exploração e coleta das primeiras chaves.
2. **Ruínas** — nova área, inimigos e mais três chaves.
3. **Templo** — confronto final contra o Guardião e recuperação da relíquia.

## 🕹️ Controles

| Tecla | Ação |
|---|---|
| `A` / `←` | Mover para a esquerda |
| `D` / `→` | Mover para a direita |
| `W` / `Espaço` | Pular |
| `X` | Atacar |
| `S` | Salvar progresso (Trabalho 2) |

## 🎯 Objetivo

Nas duas primeiras fases, o jogador deve explorar o cenário, encontrar **3 chaves**, enfrentar os inimigos e alcançar a porta de saída.

Na terceira fase, deve chegar ao templo, derrotar o **Guardião** e coletar a **Última Relíquia** para concluir o jogo.

## 🛠️ Tecnologias utilizadas

- **Java 8+**
- **Java Swing**
- **Java AWT / Java 2D**
- `JFrame`
- `JPanel`
- `Graphics`
- `BufferedImage`
- `ImageIO`
- `Timer`
- `KeyAdapter`
- `KeyEvent`
- `Rectangle`
- `ArrayList`
- Arquivos de imagem **PNG**

O projeto não utiliza uma engine de jogos externa. A lógica do jogo, movimentação, colisões, fases e interações foram implementadas em Java.

## 📁 Estrutura do projeto

```text
A_Ultima_Reliquia/
├── assets/                  (cópia de recursos do projeto)
│   ├── backgrounds/
│   └── sprites/
├── src/
│   ├── Main.java
│   ├── GamePanel.java
│   ├── Player.java
│   ├── Platform.java
│   ├── Enemy.java
│   ├── Boss.java
│   ├── Key.java
│   ├── Door.java
│   ├── Relic.java
│   ├── AssetLoader.java
│   ├── MenuPanel.java
│   ├── StoryPanel.java
│   ├── ControlsPanel.java
│   ├── MapPanel.java
│   └── VictoryPanel.java
│   └── assets/              (recursos usados pelo carregador)
│       ├── backgrounds/
│       └── sprites/
├── A_Ultima_Reliquia_Save/  (criada quando o jogo salva)
├── run.bat
├── README.md
└── .gitignore
```

## 🧩 Principais classes

### `Main`

Responsável pela janela principal e pela navegação entre Menu, História, Controles, Mapa, Jogo e Vitória.

### `GamePanel`

É o principal componente da partida. Controla o ciclo do jogo, atualização dos elementos, desenho, fases, câmera, colisões, chaves, portas, inimigos, chefe, HUD e conclusão.

### `Player`

Representa o personagem controlado pelo jogador. Controla movimentação, pulo, gravidade, direção, ataque, vida e área de colisão.

### `Platform`

Representa as plataformas e partes do cenário que possuem colisão física com o jogador.

### `Enemy`

Representa os inimigos encontrados durante as fases.

### `Boss`

Representa o Guardião da fase final, que possui uma quantidade maior de vida e precisa ser atingido várias vezes.

### `Key`

Representa as chaves coletáveis. Nas fases 1 e 2 existem três chaves.

### `Door`

Representa a passagem para a próxima fase. A porta exige as três chaves para permitir o avanço.

### `Relic`

Representa a Última Relíquia. Ela aparece após o confronto final e, ao ser coletada, encerra a aventura.

### `AssetLoader`

Classe auxiliar que centraliza o carregamento das imagens utilizadas pelo jogo.

## 💥 Sistema de colisões

As interações entre os elementos são realizadas utilizando `Rectangle`.

Cada objeto possui uma área de colisão e o jogo verifica se essas áreas se intersectam.

Exemplo:

```java
if (player.getHitbox().intersects(enemy.getHitbox())) {
    // interação entre jogador e inimigo
}
```

O sistema é utilizado em interações como jogador × plataforma, jogador × inimigo, jogador × chave, jogador × porta, jogador × relíquia e ataques × inimigos.

## 🔄 Funcionamento geral

```text
Main
 │
 └── GamePanel
      │
      ├── Player
      ├── Platforms
      ├── Enemies
      ├── Boss
      ├── Keys
      ├── Door
      └── Relic
```

O `GamePanel` atualiza o estado dos objetos e desenha o resultado continuamente.

## 🗺️ Progressão

```text
MENU
  ↓
HISTÓRIA
  ↓
MAPA
  ↓
FASE 1 — COSTA / FLORESTA
  │
  ├── Coletar 3 chaves
  ├── Enfrentar inimigos
  └── Atravessar a porta
          ↓
FASE 2 — RUÍNAS
  │
  ├── Coletar 3 chaves
  ├── Enfrentar inimigos
  └── Atravessar a porta
          ↓
FASE 3 — TEMPLO
  │
  ├── Enfrentar o Guardião
  ├── Derrotar o chefe
  └── Coletar a Última Relíquia
          ↓
TELA DE VITÓRIA
```

## 💾 Manutenção de estado — Trabalho 2

O jogo agora permite manter o progresso entre diferentes execuções.

- **CONTINUAR** no menu principal recupera um salvamento válido.
- **S** salva manualmente a partida durante o jogo.
- Autosaves são realizados em eventos importantes, como coleta de chave, dano/ataques e transição de fase.
- O salvamento registra fase, posição e vida do jogador, chaves coletadas, vida/estado dos inimigos, vida/estado do Guardião e estado da relíquia.
- Os dados são gravados em `A_Ultima_Reliquia_Save/savegame.properties`, relativo ao diretório de execução.
- O sistema usa arquivo de propriedades do Java e grava primeiro um arquivo temporário, substituindo o save após a escrita.
- Um salvamento inválido não é carregado pelo botão CONTINUAR.
- Ao concluir a aventura e coletar a relíquia, o save é removido.

## 🎨 Assets gráficos

Os elementos gráficos do jogo são utilizados como arquivos PNG.

Os assets visuais foram produzidos com **auxílio de inteligência artificial** e utilizados no projeto como recursos gráficos.

A programação da lógica, movimentação, colisões, progressão das fases e demais funcionalidades foi desenvolvida em Java.

## ▶️ Como executar

É necessário ter o **Java JDK** instalado.

A partir da pasta do projeto, os arquivos `.java` podem ser compilados e executados pela linha de comando:

```bash
javac -d bin src/*.java
java -cp bin Main
```

No Windows, também é possível executar `run.bat`, que compila e inicia o jogo.

## Trabalho 2 — Como demonstrar a manutenção de estado

1. Inicie um jogo.
2. Avance, colete uma chave ou altere o progresso.
3. Pressione `S` para salvar manualmente (mensagem de confirmação na tela).
4. Feche completamente o programa.
5. Execute novamente.
6. No menu, selecione **CONTINUAR**.
7. Verifique a fase, posição, vida, chaves e inimigos restaurados.

## 👨‍💻 Autor

**Gustavo Trevizani**

Trabalho desenvolvido para a disciplina de **Programação II** — Curso de Ciência da Computação.