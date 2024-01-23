package exercicios.aula20.exercicio6;

import java.util.Scanner;

public class JogoDaVelha {

	private static int posicaoLinha;
	private static int posicaoColuna;
	private static Character[][] tabuleiro = new Character[3][3];
	private static final char PECA_JOGADOR_A = 'X';
	private static final char PECA_JOGADOR_B = 'O';
	private static boolean rodadaJogadorA = true;
	private static boolean jogadorAVenceu = true;
	private static boolean empate = false;

	public static void main(String[] args) {
		while (ninguemVenceu()) {
			mostrarTabuleiro();
			mostrarVez();
			pegarPosicao();
			fazerJogada();
		}
		mostrarTabuleiro();
		mostrarVencedor();
	}

	private static void mostrarTabuleiro() {
		System.out.println("-------");
		for (Character[] linha : tabuleiro) {
			System.out.print("|");
			for (Character coluna : linha) {
				if (coluna != null) {
					System.out.print(coluna);
				} else {
					System.out.print(" ");
				}
				System.out.print("|");
			}
			System.out.println();
			System.out.println("-------");
		}
	}

	private static void mostrarVez() {
		System.out.println("Vez do jogador " 
							+ (rodadaJogadorA ? "A" : "B"));
	}
	
	private static void pegarPosicao() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Informe a linha: ");
		posicaoLinha = sc.nextInt() - 1;

		System.out.print("Informe a coluna: ");
		posicaoColuna = sc.nextInt() - 1;
	}

	private static void fazerJogada() {
		if (tabuleiro[posicaoLinha][posicaoColuna] != null) {
			System.out.println("O espaço está preenchido, entre outro valor");
		} else {
			tabuleiro[posicaoLinha][posicaoColuna] = rodadaJogadorA ? PECA_JOGADOR_A : PECA_JOGADOR_B;
			rodadaJogadorA = !rodadaJogadorA;
		}

	}

	private static boolean ninguemVenceu() {
		return !(algumaLinhaPreenchida() 
				|| algumaColunaPreenchida() 
				|| diagonalPrincipalPreenchida()
				|| diagonalSecundariaPreenchida()
				|| tabuleiroCheio());
	}

	private static boolean algumaLinhaPreenchida() {
		for (int linha = 0; linha < tabuleiro.length; linha++) {
			if (linhaPreenchida(linha)) {
				jogadorAVenceu = tabuleiro[linha][0] == PECA_JOGADOR_A;
				return true;
			};
		}
		return false;
	}
	
	private static boolean linhaPreenchida(int linha) {
		Character peca = tabuleiro[linha][0];
		if (peca == null) return false;
		for (int j = 1; j < tabuleiro[linha].length; j++) {
			Character atual = tabuleiro[linha][j];
			if (atual != peca) {
				return false;
			}
		}
		return true;
	}

	private static boolean algumaColunaPreenchida() {
		for (int coluna = 0; coluna < tabuleiro[0].length; coluna++) {
			if (colunaPreenchida(coluna)) {
				jogadorAVenceu = tabuleiro[0][coluna] == PECA_JOGADOR_A;
				return true;
			};
		}
		return false;
	}
	
	private static boolean colunaPreenchida(int coluna) {
		Character peca = tabuleiro[0][coluna];
		if (peca == null) return false;
		for (int i = 1; i < tabuleiro.length; i++) {
			Character atual = tabuleiro[i][coluna];
			if (atual != peca) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean diagonalPrincipalPreenchida() {
		Character peca = tabuleiro[0][0];
		if (peca == null) return false;
		for (int i = 1; i < tabuleiro.length; i++) {
			if (peca != tabuleiro[i][i]) {
				return false;
			}
		}
		jogadorAVenceu = peca == PECA_JOGADOR_A;
		return true;
	}
	
	private static boolean diagonalSecundariaPreenchida() {
		Character peca = tabuleiro[0][2];
		if (peca == null) return false;
		for (int i = 0, j = 2; i < tabuleiro.length; i++, j--) {
			if (peca != tabuleiro[i][j]) {
				return false;
			}
		}
		jogadorAVenceu = peca == PECA_JOGADOR_A;
		return true;
	}
	
	private static boolean tabuleiroCheio() {
		for (Character[] linha : tabuleiro) {
			for (Character peca : linha) {
				if (peca == null) {
					return false;
				}
			}
		}
		empate = true;
		return true;
	}
	
	private static void mostrarVencedor() {
		if (empate) {
			System.out.println("Empatou");
		} else {
			System.out.println("Jogador " 
								+ (jogadorAVenceu ? 'A' : 'B')
								+ " venceu");
		}
	}
}
