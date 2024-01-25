package exercicios.ExerciciosAula20;

import java.util.Scanner;

public class Exercicio6 {
	
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
						System.out.println("Vez do jogador: " + JOGADOR_A);// confere se é jogador a ou b
						qtdJJogadas++;
					} else if (qtnJogadas % 2 != 0) {
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
					} else {
						if (qtnJogadas % 2 == 0) {
							tabuleiro[linha - 1][coluna - 1] = JOGADOR_B;

						} else if (qtnJogadas % 2 != 0) {
							tabuleiro[linha - 1][coluna - 1] = JOGADOR_A;
						}
						jogadaCorreta = true;
					}
				}

				
				mostraTabuleiro(tabuleiro);
				jogadaCorreta = true;

			} while (flagChecagemVitoria);{ 
	            if ((tabuleiro[1][1] == 'X' && tabuleiro[1][2] == 'X' && tabuleiro[1][3] == 'X') ||     //linha 1
	                    (tabuleiro[2][1] == 'X' && tabuleiro[2][2] == 'X' && tabuleiro[2][3] == 'X') || //linha 2
	                    (tabuleiro[3][1] == 'X' && tabuleiro[3][2] == 'X' && tabuleiro[3][3] == 'X') || //linha 3
	                    (tabuleiro[1][1] == 'X' && tabuleiro[2][1] == 'X' && tabuleiro[3][1] == 'X') || //coluna 1
	                    (tabuleiro[1][2] == 'X' && tabuleiro[2][2] == 'X' && tabuleiro[3][2] == 'X') || //coluna 2
	                    (tabuleiro[1][3] == 'X' && tabuleiro[2][3] == 'X' && tabuleiro[3][3] == 'X') || //coluna 3
	                    (tabuleiro[1][1] == 'X' && tabuleiro[2][2] == 'X' && tabuleiro[3][3] == 'X') || //diagonal
	                    (tabuleiro[1][3] == 'X' && tabuleiro[2][2] == 'X' && tabuleiro[3][1] == 'X')){  //diagonal inversa
	            	jogadaCorreta = true;
	                System.out.println("Parabéns, jogador 1 ganhou!");
	            } else if ((tabuleiro[1][1] == 'O' && tabuleiro[1][2] == 'O' && tabuleiro[1][3] == 'O') ||     //linha 1
	                    (tabuleiro[2][1] == 'O' && tabuleiro[2][2] == 'O' && tabuleiro[2][3] == 'O') || //linha 2
	                    (tabuleiro[3][1] == 'O' && tabuleiro[3][2] == 'O' && tabuleiro[3][3] == 'O') || //linha 3
	                    (tabuleiro[1][1] == 'O' && tabuleiro[2][1] == 'O' && tabuleiro[3][1] == 'O') || //coluna 1
	                    (tabuleiro[1][2] == 'O' && tabuleiro[2][2] == 'O' && tabuleiro[3][2] == 'O') || //coluna 2
	                    (tabuleiro[1][3] == 'O' && tabuleiro[2][3] == 'O' && tabuleiro[3][3] == 'O') || //coluna 3
	                    (tabuleiro[1][1] == 'O' && tabuleiro[2][2] == 'O' && tabuleiro[3][3] == 'O') || //diagonal
	                    (tabuleiro[1][3] == 'O' && tabuleiro[2][2] == 'O' && tabuleiro[3][1] == 'O')){  //diagonal inversa
	            	jogadaCorreta = true;
	                System.out.println("Parabéns, jogador 2 ganhou!");
	            } else if (qtnJogadas > 9){
	            	jogadaCorreta = true;
	                System.out.println("Ninguém ganhou essa partida.");
	            } else if (qtdJJogadas > 9){
	            	jogadaCorreta = true;
	                System.out.println("Ninguém ganhou essa partida.");
	            }
			}
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