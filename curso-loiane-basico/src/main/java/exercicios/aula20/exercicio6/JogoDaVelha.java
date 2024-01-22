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
		boolean flagChecagemVitoria= true;
		
		do {
			System.out.print("Informe a linha: ");
			int linha = sc.nextInt();
			
			System.out.print("Informe a coluna: ");
			int coluna = sc.nextInt();

			mostraTabuleiro(tabuleiro);
			for(int i = 0; i < tabuleiro.length; i++)	{
				for(int j = 0 ; j < tabuleiro[i].length; j++) {
					if(tabuleiro[linha][coluna] == null) {
						tabuleiro[linha][coluna]= tabuleiro[linha][coluna];
						flagChecagemVitoria = false;
					}
				}
			}
		}while(flagChecagemVitoria);
		
		

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
