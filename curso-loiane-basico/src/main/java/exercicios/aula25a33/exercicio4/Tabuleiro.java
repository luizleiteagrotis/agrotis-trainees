package exercicios.aula25a33.exercicio4;

public class Tabuleiro {
	
	private Character[][] pecas;
	private boolean rodadaJogadorA = true;
	private boolean jogadorAVenceu = true;
	private boolean empate = false;
	private final char PECA_JOGADOR_A = 'X';
	private final char PECA_JOGADOR_B = 'O';
	
	public Tabuleiro() {
		pecas = new Character[3][3];
		rodadaJogadorA = true;
		jogadorAVenceu = true;
		empate = false;
	}
	
	public int getSize() {
		return pecas.length;
	}

	public Character[][] getPecas() {
		return pecas;
	}

	public boolean isRodadaJogadorA() {
		return rodadaJogadorA;
	}

	public void setRodadaJogadorA(boolean rodadaJogadorA) {
		this.rodadaJogadorA = rodadaJogadorA;
	}

	public boolean isJogadorAVenceu() {
		return jogadorAVenceu;
	}

	public void setJogadorAVenceu(boolean jogadorAVenceu) {
		this.jogadorAVenceu = jogadorAVenceu;
	}

	public boolean isEmpate() {
		return empate;
	}

	public void setEmpate(boolean empate) {
		this.empate = empate;
	}

	public char getPecaJogadorA() {
		return PECA_JOGADOR_A;
	}

	public char getPecaJogadorB() {
		return PECA_JOGADOR_B;
	}
}
