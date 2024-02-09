package exercicios.aula28a33;

import java.util.Scanner;

public class JogoDaVelhaTeste {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		char[][] tabuleiro = new char[5][5];
		JogoDaVelha jogo = new JogoDaVelha(tabuleiro, 0);
		
		jogo.gerarTabuleiro();
		boolean validMove = true;

		while (jogo.getTurno() <= 4) {
			for(int j = 0; j < 2; j++) {
				
				if(j == 1 && jogo.getTurno() == 4) {
					break;
				}
				
				jogo.printTabuleiro();
				
				System.out.println("Vez do Jogador " + (j+1));
				System.out.println("Selecione a posição (x,y): ");
	
				do {
					int linha = scan.nextInt();
					int coluna = scan.nextInt();
					if (jogo.getTabuleiro()[linha][coluna] == ' ') {
						
						if (j == 0) {
							jogo.getTabuleiro()[linha][coluna] = 'O';
						} else {
							jogo.getTabuleiro()[linha][coluna] = 'X';
						}
						
						if(jogo.getTurno() >= 2) {
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
			jogo.setTurno(jogo.getTurno() + 1);;
		}
		System.out.println("Empate!");
	}

}
