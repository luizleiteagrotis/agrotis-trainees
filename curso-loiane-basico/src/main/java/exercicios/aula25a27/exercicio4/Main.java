package exercicios.aula25a27.exercicio4;

import java.util.Scanner;

import exercicios.aula25a27.exercicio4.regras.RegraCheio;
import exercicios.aula25a27.exercicio4.regras.RegraColuna;
import exercicios.aula25a27.exercicio4.regras.RegraDiagonal;
import exercicios.aula25a27.exercicio4.regras.RegraLinha;

public class Main {
	
	public static void main(String[] args) {
		FinalizadorTabuleiro finalizadorTabuleiro = new FinalizadorTabuleiro();
		finalizadorTabuleiro.add(new RegraLinha());
		finalizadorTabuleiro.add(new RegraColuna());
		finalizadorTabuleiro.add(new RegraDiagonal());
		finalizadorTabuleiro.add(new RegraCheio());
		
		Tabuleiro tabuleiro = new Tabuleiro();
		
		JogoDaVelha jogoDaVelha = new JogoDaVelha(tabuleiro, finalizadorTabuleiro);
		jogoDaVelha.iniciar();
	}
}
