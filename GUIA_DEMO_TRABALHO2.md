# Guia de demonstração da manutenção de estado

1. Abra o projeto e execute `run.bat` (ou compile com `javac -d bin src/*.java` e execute `java -cp bin Main`).
2. Inicie uma nova aventura.
3. Avance pela fase, colete uma chave ou altere o progresso.
4. Pressione **S** para registrar manualmente o estado; confirme a mensagem `PROGRESSO SALVO!`.
5. Feche completamente o jogo.
6. Execute novamente.
7. No menu, escolha **CONTINUAR**.
8. Mostre a fase, posição aproximada, vida, chaves e inimigos restaurados.

O autosave também ocorre em eventos importantes (coleta de chave, danos, ataques contra inimigos/Guardião e transição de fase). Para a demonstração mais clara e previsível, use S antes de fechar.
