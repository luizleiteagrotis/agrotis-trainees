package exercicios.aula25a33.exercicio4.regras;

import exercicios.aula25a33.exercicio4.Tabuleiro;

public class RegraColuna implements RegraFinalizacao {
	
	private Tabuleiro tabuleiro;

	@Override
	public boolean verificar(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		return algumaColunaPreenchida();
	}
	
	private boolean algumaColunaPreenchida() {
		for (int coluna = 0; coluna < tabuleiro.getSize(); coluna++) {
			if (colunaPreenchida(coluna)) {
				return true;
			};
		}
		return false;
	}
	
	private boolean colunaPreenchida(int coluna) {
		Character[][] pecas = tabuleiro.getPecas();
		Character pecaJogador = pecas[0][coluna];
		if (pecaJogador == null) return false;
		for (int i = 1; i < tabuleiro.getSize(); i++) {
			Character pecaAtual = pecas[i][coluna];
			if (pecaAtual != pecaJogador) {
				return false;
			}
		}
		tabuleiro.setJogadorAVenceu(pecaJogador == tabuleiro.getPecaJogadorA());
		return true;
	}
}
