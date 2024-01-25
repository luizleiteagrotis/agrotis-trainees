package exercicios.ExerciciosAula20;

import java.util.Scanner;

public class JogoDaVelha {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Character[][] tabuleiro = new Character[3][3];
        final char JOGADOR_A = 'X';
        final char JOGADOR_B = 'O';
        int qtnJogadas = 1;

        // mostra o tabuleiro no terminal
        mostraTabuleiro(tabuleiro);
        boolean flagChecagemVitoria = true;
        boolean jogadaCorreta = false;
        int qtdJJogadas = 0;

        do {
            while (!jogadaCorreta) {
                if (qtnJogadas % 2 == 0) {
                    System.out.println("Vez do jogador: " + JOGADOR_A); // confere se é jogador a ou b
                    qtdJJogadas++;
                } else {
                    System.out.println("Vez do jogador: " + JOGADOR_B);
                    qtdJJogadas++;
                }
                qtnJogadas++;
                System.out.print("Informe a linha: ");
                int linha = sc.nextInt();

                System.out.print("Informe a coluna: ");
                int coluna = sc.nextInt();

                if (tabuleiro[linha - 1][coluna - 1] != null) {
                    System.out.println("O espaço está preenchido, entre outro valor");
                } else if (qtnJogadas % 2 == 0) {
                    tabuleiro[linha - 1][coluna - 1] = JOGADOR_B;
                } else {
                    tabuleiro[linha - 1][coluna - 1] = JOGADOR_A;
                }
                jogadaCorreta = true;
            }
        

            mostraTabuleiro(tabuleiro);
            jogadaCorreta = false;
            
            if ((tabuleiro[0][0] != null && tabuleiro[0][0] == 'X' && tabuleiro[0][1] != null && tabuleiro[0][1] == 'X' && tabuleiro[0][2] != null && tabuleiro[0][2] == 'X') || // linha 1
            	    (tabuleiro[1][0] != null && tabuleiro[1][0] == 'X' && tabuleiro[1][1] != null && tabuleiro[1][1] == 'X' && tabuleiro[1][2] != null && tabuleiro[1][2] == 'X') || // linha 2
            	    (tabuleiro[2][0] != null && tabuleiro[2][0] == 'X' && tabuleiro[2][1] != null && tabuleiro[2][1] == 'X' && tabuleiro[2][2] != null && tabuleiro[2][2] == 'X') || // linha 3
            	    (tabuleiro[0][0] != null && tabuleiro[0][0] == 'X' && tabuleiro[1][0] != null && tabuleiro[1][0] == 'X' && tabuleiro[2][0] != null && tabuleiro[2][0] == 'X') || // coluna 1
            	    (tabuleiro[0][1] != null && tabuleiro[0][1] == 'X' && tabuleiro[1][1] != null && tabuleiro[1][1] == 'X' && tabuleiro[2][1] != null && tabuleiro[2][1] == 'X') || // coluna 2
            	    (tabuleiro[0][2] != null && tabuleiro[0][2] == 'X' && tabuleiro[1][2] != null && tabuleiro[1][2] == 'X' && tabuleiro[2][2] != null && tabuleiro[2][2] == 'X') || // coluna 3
            	    (tabuleiro[0][0] != null && tabuleiro[0][0] == 'X' && tabuleiro[1][1] != null && tabuleiro[1][1] == 'X' && tabuleiro[2][2] != null && tabuleiro[2][2] == 'X') || // diagonal
            	    (tabuleiro[0][2] != null && tabuleiro[0][2] == 'X' && tabuleiro[1][1] != null && tabuleiro[1][1] == 'X' && tabuleiro[2][0] != null && tabuleiro[2][0] == 'X')) { // diagonal inversa
            	    System.out.println("Parabéns, jogador 1 ganhou!");
            	    flagChecagemVitoria = false;
            	} else if ((tabuleiro[0][0] != null && tabuleiro[0][0] == 'O' && tabuleiro[0][1] != null && tabuleiro[0][1] == 'O' && tabuleiro[0][2] != null && tabuleiro[0][2] == 'O') || // linha 1
            	           (tabuleiro[1][0] != null && tabuleiro[1][0] == 'O' && tabuleiro[1][1] != null && tabuleiro[1][1] == 'O' && tabuleiro[1][2] != null && tabuleiro[1][2] == 'O') || // linha 2
            	           (tabuleiro[2][0] != null && tabuleiro[2][0] == 'O' && tabuleiro[2][1] != null && tabuleiro[2][1] == 'O' && tabuleiro[2][2] != null && tabuleiro[2][2] == 'O') || // linha 3
            	           (tabuleiro[0][0] != null && tabuleiro[0][0] == 'O' && tabuleiro[1][0] != null && tabuleiro[1][0] == 'O' && tabuleiro[2][0] != null && tabuleiro[2][0] == 'O') || // coluna 1
            	           (tabuleiro[0][1] != null && tabuleiro[0][1] == 'O' && tabuleiro[1][1] != null && tabuleiro[1][1] == 'O' && tabuleiro[2][1] != null && tabuleiro[2][1] == 'O') || // coluna 2
            	           (tabuleiro[0][2] != null && tabuleiro[0][2] == 'O' && tabuleiro[1][2] != null && tabuleiro[1][2] == 'O' && tabuleiro[2][2] != null && tabuleiro[2][2] == 'O') || // coluna 3
            	           (tabuleiro[0][0] != null && tabuleiro[0][0] == 'O' && tabuleiro[1][1] != null && tabuleiro[1][1] == 'O' && tabuleiro[2][2] != null && tabuleiro[2][2] == 'O') || // diagonal
            	           (tabuleiro[0][2] != null && tabuleiro[0][2] == 'O' && tabuleiro[1][1] != null && tabuleiro[1][1] == 'O' && tabuleiro[2][0] != null && tabuleiro[2][0] == 'O')) { // diagonal inversa
            	    System.out.println("Parabéns, jogador 2 ganhou!");
            	    flagChecagemVitoria = false;
            	} else if (qtdJJogadas > 9) {
            	    System.out.println("Ninguém ganhou essa partida.");
            	    flagChecagemVitoria = false;
            	}
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
}