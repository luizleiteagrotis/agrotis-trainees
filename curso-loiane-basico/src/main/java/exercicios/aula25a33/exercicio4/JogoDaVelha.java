package exercicios.aula25a33.exercicio4;

import java.util.Scanner;

public class JogoDaVelha {
	
	private Tabuleiro tabuleiro;
	private FinalizadorTabuleiro finalizador;
	private int posicaoLinha;
	private int posicaoColuna;
	private Scanner scanner;
	
	public JogoDaVelha(Tabuleiro tabuleiro, FinalizadorTabuleiro finalizador) {
		this.tabuleiro = tabuleiro;
		this.finalizador = finalizador;
		this.scanner = new Scanner(System.in);
	}
	
	public void iniciar() {
		while (naoFinalizou()) {
			mostrarTabuleiro();
			mostrarVez();
			pegarPosicao();
			fazerJogada();
			System.out.println();
		}
		mostrarTabuleiro();
		mostrarVencedor();
	}
	
	private boolean naoFinalizou() {
		return finalizador.naoFinalizou(tabuleiro);
	}
	
	private void mostrarTabuleiro() {
		System.out.println("-------");
		for (Character[] linha : tabuleiro.getPecas()) {
			System.out.print("|");
			for (Character peca : linha) {
				if (peca != null) {
					System.out.print(peca);
				} else {
					System.out.print(" ");
				}
				System.out.print("|");
			}
			System.out.println();
			System.out.println("-------");
		}
	}
	
	private void mostrarVez() {
		char pecaJogador = tabuleiro.isRodadaJogadorA() ? tabuleiro.getPecaJogadorA() : tabuleiro.getPecaJogadorB(); 
		System.out.println("Vez do jogador " + pecaJogador);
	}
	
	private void pegarPosicao() {
		posicaoLinha = pegarValor("Informe a linha: ");
		posicaoColuna = pegarValor("Informe a coluna: ");
	}
	
	private int pegarValor(String mensagem) {
		int valor = -1;
		while (!estaNoTabuleiro(valor)) {
			System.out.print(mensagem);
			valor = scanner.nextInt();
			if (!estaNoTabuleiro(valor)) {
				System.out.println("Valor invalido!");
			}
		}
		return valor - 1;
	}
	
	private boolean estaNoTabuleiro(int i) {
		return 0 < i && i < tabuleiro.getSize() + 1;
	}
	
	private void fazerJogada() {
		Character[][] pecas = tabuleiro.getPecas();
		char pecaA = tabuleiro.getPecaJogadorA();
		char pecaB = tabuleiro.getPecaJogadorB();
		if (pecas[posicaoLinha][posicaoColuna] == null) {
			Character peca = tabuleiro.isRodadaJogadorA() ? pecaA : pecaB;
			pecas[posicaoLinha][posicaoColuna] = peca;
			trocarRodada();
		} else {
			System.out.println("Posicao ja ocupada!");
		}
	}
	
	private void trocarRodada() {
		tabuleiro.setRodadaJogadorA(!tabuleiro.isRodadaJogadorA());
	}
	
	private void mostrarVencedor() {
		if (tabuleiro.isEmpate()) {
			System.out.println("Empatou");
		} else {
			System.out.println("Jogador " 
								+ (tabuleiro.isJogadorAVenceu() ? tabuleiro.getPecaJogadorA() : tabuleiro.getPecaJogadorB())
								+ " venceu");
		}
	}
}
