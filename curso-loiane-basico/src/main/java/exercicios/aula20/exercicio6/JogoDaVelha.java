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
					
					System.out.println("Vez do jogador: " + JOGADOR_A);// confere se é jogador a ou b
					
				} else if (qtnJogadas % 2 != 0) {
					
					System.out.println("Vez do jogador: " + JOGADOR_B);
					
				}				
				System.out.print("Informe a linha: ");
				int linha = sc.nextInt();

				System.out.print("Informe a coluna: ");
				int coluna = sc.nextInt();
				if(linha <= 0 || linha > 3 || coluna <= 0 || coluna>3) {
					System.out.println("Numero inválido");
					continue;
				}
				if (tabuleiro[linha - 1][coluna - 1] != null) {
					System.out.println("O espaço está preenchido, entre outro valor");
					mostraTabuleiro(tabuleiro);
				} else {
					if (qtnJogadas % 2 == 0) {
						tabuleiro[linha - 1][coluna - 1] = JOGADOR_A;

					} else if (qtnJogadas % 2 != 0) {
						tabuleiro[linha - 1][coluna - 1] = JOGADOR_B;
					}
					jogadaCorreta = true;
				}
			}
			mostraTabuleiro(tabuleiro);
			//verificar por que não valida a coluna
			//melhorar sistema de mensagem do vencedor
			
			flagChecagemVitoria = verificarVitoria(tabuleiro);
			if(qtnJogadas == 8) {
				System.out.println("Empate!");
				flagChecagemVitoria = false;
			}
			qtnJogadas++;
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
	
	public static boolean verificarVitoria(Character[][] tabuleiro) {
		
		if(tabuleiro[0][0] != null && tabuleiro [0][0] == tabuleiro[1][1] && tabuleiro [0][0] == tabuleiro[2][2]) {
			System.out.println("Jogador " + tabuleiro[0][0] + " Ganhou");
			return false;
		}
		
		if(tabuleiro[0][2] != null && tabuleiro [0][2] == tabuleiro[1][1] && tabuleiro [0][2] == tabuleiro[2][0]) {
			System.out.println("Jogador " + tabuleiro[0][2] + " Ganhou");
			return false;
		}
		for(int i=0; i < tabuleiro.length ; i++) {
			int contLinha=1;
			int contColuna=1;
			
			for(int j=0; j < tabuleiro[i].length -1 ; j++) {
				
				//verificação linha
				if(tabuleiro[i][j] != null && tabuleiro[i][j] == tabuleiro[i][j+1]) {
					contLinha++;					
					if (contLinha == 3) {
						
						System.out.println("Jogador " + tabuleiro[i][j] + " Ganhou");
						return false;
					}
					
				} else {
					contLinha = 1;
				}
				
				if(tabuleiro[j][i] != null && tabuleiro[j][i] == tabuleiro[j+1][i]) {	
					
					contColuna++;					
					if (contColuna == 3) {
						
						System.out.println("Jogador " + tabuleiro[j][i] + " Ganhou");
						return false;
					}
					
				} else {
					contColuna = 1;
				}
			}
			
		}
		
		return true;
	}
	
}
