package exercicios.aula25a33.exercicio4.regras;

import exercicios.aula25a33.exercicio4.Tabuleiro;

public class RegraCheio implements RegraFinalizacao {
	
	@Override
	public boolean verificar(Tabuleiro tabuleiro) {
		Character[][] pecas = tabuleiro.getPecas();
		for (Character[] linha : pecas) {
			for (Character peca : linha) {
				if (peca == null) {
					return false;
				}
			}
		}
		tabuleiro.setEmpate(true);
		return true;
	}
}
