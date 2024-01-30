package atividades25_27;

import java.util.Scanner;

public class JogoDaVelha {

    public static void mostraTabuleiro(Character[][] tabuleiro) {
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

    public static void jogoDaVelha() {

        Scanner sc = new Scanner(System.in);
        Character[][] tabuleiro = new Character[3][3];
        final char JOGADOR_A = 'X';
        final char JOGADOR_B = 'O';
        int qtnJogadas = 0;
        int linha = 0;
        int coluna = 0;
        int qtnIgual = 0;

        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                tabuleiro[i][j] = ' ';
            }
        }

        // mostra o tabuleiro no terminal
        mostraTabuleiro(tabuleiro);
        boolean flagChecagemVitoria = true;
        boolean jogadaCorreta = false;

        do {

            while (!jogadaCorreta) {
                if (qtnJogadas % 2 == 0) {
                    System.out.println("Vez do jogador: " + JOGADOR_B);
                } else {
                    System.out.println("Vez do jogador: " + JOGADOR_A);
                }

                System.out.print("Informe a linha: ");
                linha = sc.nextInt();

                System.out.print("Informe a coluna: ");
                coluna = sc.nextInt();

                if (tabuleiro[linha - 1][coluna - 1] != ' ') {
                    System.out.println("O espaço está preenchido, entre outro valor");
                } else {
                    if (qtnJogadas % 2 == 0) {
                        tabuleiro[linha - 1][coluna - 1] = JOGADOR_B;

                    } else {
                        tabuleiro[linha - 1][coluna - 1] = JOGADOR_A;
                    }
                    jogadaCorreta = true;
                    qtnJogadas++;
                    System.out.println(qtnJogadas);
                }
            }

            // verificador vencedor linha
            for (int i = 0; i < tabuleiro.length; i++) {
                for (int j = 0; j < tabuleiro[i].length; j++) {
                    if (tabuleiro[i][j] == ' ') {
                        break;
                    }
                    if (tabuleiro[i][j].equals(tabuleiro[linha - 1][coluna - 1])) {
                        qtnIgual++;
                    }
                }
                if (qtnIgual == 3) {
                    System.out.println("Temos um vencedor de linha");
                    flagChecagemVitoria = false;
                    break;
                } else {
                    qtnIgual = 0;
                }
            }

            // verificador vencedor coluna
            for (int i = 0; i < tabuleiro.length; i++) {
                for (int j = 0; j < 3; j++) {
                    if (tabuleiro[j][i] == ' ') {
                        break;
                    }
                    if (tabuleiro[j][i].equals(tabuleiro[linha - 1][coluna - 1])) {
                        qtnIgual++;
                    }
                }
                if (qtnIgual == 3) {
                    System.out.println("Temos um vencedor de coluna");
                    flagChecagemVitoria = false;
                    break;
                } else {
                    qtnIgual = 0;
                }
            }

            // verificador vencedor diagonal
            if (tabuleiro[1][1] == ' ') {
            } else {
                if (tabuleiro[1][1].equals(tabuleiro[linha - 1][coluna - 1])
                                && tabuleiro[0][0].equals(tabuleiro[linha - 1][coluna - 1])
                                && tabuleiro[2][2].equals(tabuleiro[linha - 1][coluna - 1])) {
                    System.out.println("Temos um vencedor de diagonal");
                    flagChecagemVitoria = false;
                } else {
                    if (tabuleiro[1][1].equals(tabuleiro[linha - 1][coluna - 1])
                                    && tabuleiro[0][2].equals(tabuleiro[linha - 1][coluna - 1])
                                    && tabuleiro[2][0].equals(tabuleiro[linha - 1][coluna - 1])) {
                        System.out.println("Temos um vencedor de diagonal");
                        flagChecagemVitoria = false;
                    }
                }
            }

            mostraTabuleiro(tabuleiro);
            jogadaCorreta = false;

            if (qtnJogadas == 9 && flagChecagemVitoria) {
                System.out.println("Deu velha");
                flagChecagemVitoria = false;
            }

        } while (flagChecagemVitoria);

        sc.close();
    }
}
