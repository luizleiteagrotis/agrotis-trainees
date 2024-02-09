package exercicios.aula25a27;

public class JogoDaVelha {

	public char[][] tabuleiro;
	public int turno;
	
	public void gerarTabuleiro() {
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				if(i%2 != 0 ) {
					tabuleiro[i][j] = '-';
				}else if(j%2 != 0){
					tabuleiro[i][j] = '|';
				}else {
					tabuleiro[i][j] = ' ';

				}
			}
		} 
	}
	
	public boolean checkWinningCondition() {
		if(tabuleiro[0][0] == tabuleiro[0][2] && tabuleiro[0][0] == tabuleiro[0][4] && tabuleiro[0][0] != ' ') {
			return true;
		}
		if(tabuleiro[0][0] == tabuleiro[2][0] && tabuleiro[0][0] == tabuleiro[4][0] && tabuleiro[0][0] != ' ') {
			return true;
		}
		if(tabuleiro[0][0] == tabuleiro[2][2] && tabuleiro[0][0] == tabuleiro[4][4] && tabuleiro[0][0] != ' ') {
			return true;
		}
		//
		if(tabuleiro[0][2] == tabuleiro[2][2] && tabuleiro[0][2] == tabuleiro[4][2] && tabuleiro[0][2] != ' ') {
			return true;
		}
		//
		if(tabuleiro[0][4] == tabuleiro[2][4] && tabuleiro[0][4] == tabuleiro[4][4] && tabuleiro[0][4] != ' ') {
			return true;
		}
		if(tabuleiro[0][4] == tabuleiro[2][2] && tabuleiro[0][4] == tabuleiro[4][0] && tabuleiro[0][4] != ' ') {
			return true;
		}
		//
		if(tabuleiro[2][0] == tabuleiro[2][2] && tabuleiro[2][0] == tabuleiro[2][4] && tabuleiro[2][0] != ' ') {
			return true;
		}
		//
		if(tabuleiro[4][0] == tabuleiro[4][2] && tabuleiro[4][0] == tabuleiro[4][4] && tabuleiro[4][0] != ' ') {
			return true;
		}
		
		return false;
	}
	
	public void printTabuleiro() {
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int k = 0; k < tabuleiro[i].length; k++) {
				System.out.print(tabuleiro[i][k]);
			}
			System.out.println("");
		}
	}
	
}
