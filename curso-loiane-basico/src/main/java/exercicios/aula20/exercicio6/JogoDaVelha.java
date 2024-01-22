package exercicios.aula20.exercicio6;

import java.util.Scanner;

public class JogoDaVelha {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Character[][] tabuleiro = new Character[3][3];
		final char JOGADOR_A = 'X';
		final char JOGADOR_B = 'O';
		int qtnJogadas = 0;
		

		// mostra o tabuleiro no terminal
		mostraTabuleiro(tabuleiro);
		boolean flagChecagemVitoria = true;
		boolean jogadaCorreta = false;

		do {

			while (!jogadaCorreta) {
				if (qtnJogadas % 2 == 0) {
					System.out.println("Vez do jogador: " + JOGADOR_B);//confere se é jogador a ou b

				} else if (qtnJogadas % 2 != 0){
					System.out.println("Vez do jogador: " + JOGADOR_A);
				}
				qtnJogadas++;
				System.out.print("Informe a linha: ");
				int linha = sc.nextInt();

				System.out.print("Informe a coluna: ");
				int coluna = sc.nextInt();

				if (tabuleiro[linha - 1][coluna - 1] != null) {
					System.out.println("O espaço está preenchido, entre outro valor");
				} else {
					if (qtnJogadas % 2 == 0) {
						tabuleiro[linha - 1][coluna - 1] = JOGADOR_B;

					} else if (qtnJogadas % 2 != 0){
						tabuleiro[linha - 1][coluna - 1] = JOGADOR_A;
					}
					jogadaCorreta = true;
				}
			}

			mostraTabuleiro(tabuleiro);
			jogadaCorreta = false;

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
