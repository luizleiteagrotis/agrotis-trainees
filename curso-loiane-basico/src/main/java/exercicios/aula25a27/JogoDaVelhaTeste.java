package exercicios.aula25a27;

import java.util.Scanner;

public class JogoDaVelhaTeste {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		JogoDaVelha jogo = new JogoDaVelha();
		jogo.tabuleiro = new char[5][5];
		jogo.gerarTabuleiro();
		boolean validMove = true;

		while (jogo.turno <= 4) {
			for(int j = 0; j < 2; j++) {
				
				if(j == 1 && jogo.turno == 4) {
					break;
				}
				
				jogo.printTabuleiro();
				
				System.out.println("Vez do Jogador " + (j+1));
				System.out.println("Selecione a posição (x,y): ");
	
				do {
					int linha = scan.nextInt();
					int coluna = scan.nextInt();
					if (jogo.tabuleiro[linha][coluna] == ' ') {
						
						if (j == 0) {
							jogo.tabuleiro[linha][coluna] = 'O';
						} else {
							jogo.tabuleiro[linha][coluna] = 'X';
						}
						
						if(jogo.turno >= 2) {
							if(jogo.checkWinningCondition() == true) {
								System.out.println("Jogador " + (j+1) + " ganhou a partida!");
								System.exit(0);
							}
						}
						validMove = true;
					} else {
						
						validMove = false;
						System.out.println("Posição inválida! Tente novamente: ");
						
					} 
					
				} while(!validMove);
			}
			jogo.turno++;
		}
		System.out.println("Empate!");
	}

}
