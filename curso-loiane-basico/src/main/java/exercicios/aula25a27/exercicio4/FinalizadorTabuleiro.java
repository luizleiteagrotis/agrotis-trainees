package exercicios.aula25a27.exercicio4;

import java.util.ArrayList;
import java.util.List;

import exercicios.aula25a27.exercicio4.regras.RegraFinalizacao;

public class FinalizadorTabuleiro {
	
	private List<RegraFinalizacao> regras;
	
	public FinalizadorTabuleiro() {
		regras = new ArrayList<RegraFinalizacao>();
	}
	
	public boolean naoFinalizou(Tabuleiro tabuleiro) {
		for (int i = 0; i < regras.size(); i++) {
			RegraFinalizacao regra = regras.get(i);
			if (regra.verificar(tabuleiro)) {
				return false;
			}
		}
		return true;
	}
	
	public void add(RegraFinalizacao regra) {
		regras.add(regra);
	}
}
