# ☕Aula 20 - Exercício 06 - Coding Dojo 01

## 🥋 Coding Dojo

[Dojo](https://www.devmedia.com.br/o-que-e-o-coding-dojo/30517), (pronuncia-se Dojô) é uma palavra de origem japonesa e significa “local de treinamento”.
Portanto, o Coding Dojo nada mais é que do um “local de treinamento de código”, ou “local de treinamento de programação”.

Existem basicamente três formatos básicos: Kata, Randori e Kake.

Escolhemos o Randori para este Dojo pelo pouco tempo necessário para organização e execução.

No Randori são escolhidas duas pessoas para ficar à frente da plateia.
Uma é o piloto (quem escreve o código), outra é o co-piloto (quem orienta o piloto).

Separamos 2h para a dinâmica, sendo 10 min para a explicação inicial, e 10 min para cada dupla poder contribuir com o código.
Tendo 10 participantes, espera-se a execução de 10 rodadas de 10 min.

O apresentador deve controlar o tempo de cada dupla.


## 📝 Enunciado do Desafio

Faça um programa para jogar o jogo da velha.
O programa deve permitir que dois jogadores façam uma partida de jogo da velha, usando o computador para ver o tabuleiro.
Cada jogador vai alternadamente informando a posição onde deseja colocar a sua peça ('O' ou 'X').
O programa deve impedir jogadas inválidas e determinar automaticamente quando o jogo terminou e quem foi o vencedor (jogador1 ou jogador2).
A cada nova jogada, o programa deve atualizar a situação do tabuleiro na tela.


## 🔄 Se 2h não forem suficientes

Neste caso, uma opção é, das 10 rodadas, escolher as pares ou as ímpares, para que as duplas dessas rodadas possam terminar de onde paramos.

A entrega se dará por meio de uma branch retirada da branch dojo-1 conforme os passos abaixo.

### 1. Pelo terminal, acesse a pasta padrão
```
cd Desenvolvimento/backend
```

### 2. Clone este repositorio
```
git clone https://github.com/luizleiteagrotis/agrotis-trainees.git
```

### 3. Entre na pasta recém criada
```
cd agrotis-trainees
```

### 4. Entre na branch dojo-1
```
git checkout dojo-1
```

### 5. Garanta que a branch esteja atualizada localmente
```
git pull
```

### 6. Crie uma branch a partir da dojo-1 com os nomes da dupla
```
git branch nome.1-nome.2
```

Exemplo
```
git branch kayo-anderson
```

### 7. Entre na branch recém criada
```
git checkout nome.1-nome.2
```

Exemplo
```
git checkout kayo-anderson
```

### 8. Termine o código e então adicione todas as alterações ao stage
```
git add .
```

### 9. Faça commit das alterações no stage
```
git commit -m "mensagem detalhada"
```

### 10. Suba as alterações para o repositório do github
```
git push origin nome.1-nome.2
```

Exemplo
```
git push origin kayo-anderson
```

## 💡 Dicas úteis Git

Caso já tenha começado a mexer no código antes de criar a branch correta, faça os passo abaixo:
### 1. Salve as alterações locais no stash
```
git stash
```

### 2. Garanta que esteja na branch original atualizada
```
git fetch && git checkout dojo-1 && git pull
```

### 3. Crie uma branch a partir da dojo-1 com os nomes da dupla e entre nela
```
git checkout -b nome.1-nome.2
```

Exemplo
```
git checkout -b kayo-anderson
```

### 4. Resgate as alterações salvas no stash
```
git stash pop
```

### 5. Siga do passo 9 em diante