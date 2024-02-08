
package exercicios.aula20;

import java.util.Scanner;

class Exercicio6{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		char[][] A = new char[5][5];
		
		int t = 0;
		
		boolean validMove = true;
	
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				if(i%2 != 0 ) {
					A[i][j] = '-';
				}else if(j%2 != 0){
					A[i][j] = '|';
				}else {
					A[i][j] = ' ';

				}
			}
		} 
		
		while (t <= 4) {
			for(int j = 0; j < 2; j++) {
				
				if(j == 1 && t == 4) {
					break;
				}
					
				for (int i = 0; i < A.length; i++) {
					for (int k = 0; k < A[i].length; k++) {
						System.out.print(A[i][k]);
					}
					System.out.println("");
				}
				
				System.out.println("Vez do Jogador " + (j+1));
				System.out.println("Selecione a posição (x,y): ");
	
				do {
					int linha = scan.nextInt();
					int coluna = scan.nextInt();
					if (A[linha][coluna] == ' ') {
						
						if (j == 0) {
							A[linha][coluna] = 'O';
						} else {
							A[linha][coluna] = 'X';
						}
						
						if(t >= 2) {
							if(checkWinningCondition(A) == true) {
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
			t++;
		}
		System.out.println("Empate!");
		
	}

	private static boolean checkWinningCondition(char[][] A) {
		
		if(A[0][0] == A[0][2] && A[0][0] == A[0][4] && A[4][0] != ' ') {
			return true;
		}
		if(A[0][0] == A[2][0] && A[0][0] == A[4][0] && A[4][0] != ' ') {
			return true;
		}
		if(A[0][0] == A[2][2] && A[0][0] == A[4][4] && A[4][0] != ' ') {
			return true;
		}
		//
		if(A[0][2] == A[2][2] && A[0][2] == A[4][2] && A[4][0] != ' ') {
			return true;
		}
		//
		if(A[0][4] == A[2][4] && A[0][4] == A[4][4] && A[4][0] != ' ') {
			return true;
		}
		if(A[0][4] == A[2][2] && A[0][4] == A[4][0] && A[4][0] != ' ') {
			return true;
		}
		//
		if(A[2][0] == A[2][2] && A[2][0] == A[2][4] && A[4][0] != ' ') {
			return true;
		}
		//
		if(A[4][0] == A[4][2] && A[4][0] == A[4][4] && A[4][0] != ' ') {
			return true;
		}
		
		return false;
	}
}
