package exercicios.aula25a33.exercicio4.regras;

import exercicios.aula25a33.exercicio4.Tabuleiro;

public class RegraLinha implements RegraFinalizacao {

	private Tabuleiro tabuleiro;
	
	@Override
	public boolean verificar(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		return algumaLinhaPreenchida();
	}
	
	private boolean algumaLinhaPreenchida() {
		for (int linha = 0; linha < tabuleiro.getSize(); linha++) {
			if (linhaPreenchida(linha)) {
				return true;
			};
		}
		return false;
	}
	
	private boolean linhaPreenchida(int i) {
		Character[][] pecas = tabuleiro.getPecas();
		Character pecaJogador = pecas[i][0];
		if (pecaJogador == null) return false;
		for (int j = 1; j < pecas[i].length; j++) {
			Character pecaAtual = pecas[i][j];
			if (pecaAtual != pecaJogador) {
				return false;
			}
		}
		tabuleiro.setJogadorAVenceu(pecaJogador == tabuleiro.getPecaJogadorA());
		return true;
	}
	
}
