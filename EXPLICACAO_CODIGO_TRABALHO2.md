# Explicação das alterações — Trabalho 2

## Conceito
Antes, os estados existiam somente na memória do processo. Ao encerrar o jogo, as variáveis e objetos eram perdidos. Persistência significa registrar dados em armazenamento que continua existindo depois que o programa termina e, na próxima execução, ler esses dados para reconstruir o estado.

## SaveManager.java
Centraliza a responsabilidade de persistência. `SaveData` é uma estrutura simples que reúne os valores necessários para reconstrução. `save()` grava propriedades em arquivo; primeiro escreve em um arquivo temporário e depois o substitui, reduzindo o risco de deixar o arquivo principal parcialmente escrito. `load()` lê e valida versão, limites e tipos. Problemas são sinalizados por IOException. `saveExists()` e `deleteSave()` oferecem operações auxiliares.

## GamePanel.java
`captureState()` lê fase, posição/vida do Player, chaves, inimigos, Guardião e relíquia, montando SaveData. `restoreState()` aplica esses valores aos objetos depois que `setupPhase()` reconstrói a fase. `autoSave()` grava em eventos importantes; `saveStateWithFeedback()` é chamado quando S é pressionada. A mensagem de confirmação é desenhada temporariamente na tela.

## Player.java
`restoreHealth()` reaplica a vida e atualiza `alive`, evitando incoerência entre vida e estado de sobrevivência. `setPosition()` já existia e é reutilizado para recuperar coordenadas.

## Key.java / Enemy.java / Boss.java / Relic.java
Foram adicionados métodos para aplicar estado recuperado. Inimigos restauram sua vida e o estado vivo é derivado da vida; chaves e relíquia restauram coleta; Guardião recupera sua vida e estado.

## Main.java e MenuPanel.java
`continueGame()` lê o save e cria um GamePanel com os dados. A opção CONTINUAR é habilitada quando existe um arquivo que pode ser carregado e o jogador tem vida positiva. `startGame()` inicia uma partida nova e remove o save anterior para evitar misturar partidas.

## Eventos de autosave
- Coleta de chave.
- Dano recebido pelo jogador.
- Dano causado a inimigo.
- Dano causado ao Guardião.
- Transição entre fases.

## Limitações/observações
- O salvamento manual via S registra a posição atual, tornando-o o modo mais explícito de demonstrar a recuperação.
- Os autosaves são eventos, não gravações contínuas por frame.
- O arquivo é relativo ao diretório de execução e fica na pasta `A_Ultima_Reliquia_Save`.
- Uma conclusão bem-sucedida da aventura remove o save.
