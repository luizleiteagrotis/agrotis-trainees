package exercicios.aula25a33.exercicio4.regras;

import exercicios.aula25a33.exercicio4.Tabuleiro;

public class RegraDiagonal implements RegraFinalizacao {

	private Tabuleiro tabuleiro;
	
	@Override
	public boolean verificar(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		return diagonalPrincipalPreenchida() || diagonalSecundariaPreenchida();
	}
	
	private boolean diagonalPrincipalPreenchida() {
		Character[][] pecas = tabuleiro.getPecas();
		Character pecaJogador = pecas[0][0];
		if (pecaJogador == null) return false;
		for (int i = 1; i < tabuleiro.getSize(); i++) {
			Character pecaAtual = pecas[i][i];
			if (pecaJogador != pecaAtual) {
				return false;
			}
		}
		tabuleiro.setJogadorAVenceu(pecaJogador == tabuleiro.getPecaJogadorA());
		return true;
	}
	
	private boolean diagonalSecundariaPreenchida() {
		Character[][] pecas = tabuleiro.getPecas();
		Character pecaJogador = pecas[0][pecas.length - 1];
		if (pecaJogador == null) return false;
		for (int i = 1, j = pecas.length - 2 ; i < tabuleiro.getSize(); i++, j--) {
			Character pecaAtual = pecas[i][j];
			if (pecaJogador != pecaAtual) {
				return false;
			}
		}
		tabuleiro.setJogadorAVenceu(pecaJogador == tabuleiro.getPecaJogadorA());
		return true;
	}
}
