package exercicios.aula28a33;

public class JogoDaVelha {
	
	private char[][] tabuleiro;
	private int turno;

	public JogoDaVelha(char[][] tabuleiro, int turno) {
		super();
		this.tabuleiro = tabuleiro;
		this.turno = turno;
	}
	
	public char[][] getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(char[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
	
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

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}
	
}

