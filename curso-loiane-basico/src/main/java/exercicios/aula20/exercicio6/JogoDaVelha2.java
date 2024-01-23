package exercicios.aula20.exercicio6;

import java.util.Scanner;

public class JogoDaVelha2 {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Character[][] tabuleiro = new Character[3][3];
        final char JOGADOR_A = 'X';
        final char JOGADOR_B = 'O';
        int qtnJogadas = 0;

        mostraTabuleiro(tabuleiro);
        boolean flagChecagemVitoria = true;
        boolean jogadaCorreta = false;
        
        for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				tabuleiro[i][j] = ' ';
			}
		}

        do {
            while (!jogadaCorreta) {
                if (qtnJogadas % 2 == 0) {
                    System.out.println("Vez do jogador: " + JOGADOR_A);
                } else {
                    System.out.println("Vez do jogador: " + JOGADOR_B);
                }

                System.out.print("Informe a linha: ");
                int linha = sc.nextInt();

                System.out.print("Informe a coluna: ");
                int coluna = sc.nextInt();

                if (tabuleiro[linha - 1][coluna - 1] != ' ') {
                    System.out.println("O espaço está preenchido, escolha outro valor.");
                } else {
                    if (qtnJogadas % 2 == 0) {
                        tabuleiro[linha - 1][coluna - 1] = JOGADOR_A;
                    } else {
                        tabuleiro[linha - 1][coluna - 1] = JOGADOR_B;
                    }
                    jogadaCorreta = true;
                }
            }

            mostraTabuleiro(tabuleiro);

            if (verificaVitoria(tabuleiro, JOGADOR_A) || verificaVitoria(tabuleiro, JOGADOR_B)) {
                flagChecagemVitoria = false;
                System.out.println("Jogador " + (qtnJogadas % 2 == 0 ? JOGADOR_A : JOGADOR_B) + " venceu!");
            } else if (qtnJogadas == 9) {
                flagChecagemVitoria = false;
                System.out.println("O jogo terminou em empate!");
            }

            jogadaCorreta = false;
            qtnJogadas++;

        } while (flagChecagemVitoria);
    }

    private static void mostraTabuleiro(Character[][] tabuleiro) {
        System.out.println("-------");
        for (Character[] linha : tabuleiro) {
            System.out.print("|");
            for (Character coluna : linha) {
                if (coluna != null) {
                    System.out.print(coluna);
                } else {
                    System.out.print(" ");
                }
                System.out.print("|");
            }
            System.out.println();
            System.out.println("-------");
        }
    }

    private static boolean verificaVitoria(Character[][] tabuleiro, char jogador) {
        for (int i = 0; i < 3; i++) {
            if ((tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador) ||
                (tabuleiro[0][i] == jogador && tabuleiro[1][i] == jogador && tabuleiro[2][i] == jogador)) {
                return true;
            }
        }

        
        if ((tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) ||
            (tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador)) {
            return true;
        }

        return false;
    }
}

