package exercicios.aula20.exercicio6;

import java.util.Scanner;

public class JogoDaVelha {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Character[][] tabuleiro = new Character[3][3];
		final char JOGADOR_A = 'X';
		final char JOGADOR_B = 'O';
		
		// mostra o tabuleiro no terminal
		mostraTabuleiro(tabuleiro);
		
		sc.close();
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
