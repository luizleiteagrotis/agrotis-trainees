package exercicios.aula25a27.exercicio4;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		JogoVelha jogoVelha = new JogoVelha();

		String[][] tabuleiro = jogoVelha.getTabuleiro();

		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				tabuleiro[i][j] = " ";
			}
		}

		System.out.println("Qual jogador vai começar ?? X ou O");
		jogoVelha.setJogador(scan.next());

		while (!jogoVelha.getVencedor()) {

			while (!jogoVelha.getJogadaValida()) {
				System.out.println(" -- " + jogoVelha.getJogador() + " -- ");
				System.out.println("Digite a linha e a coluna que deseja jogar: ");
				jogoVelha.setLinha(scan.nextInt());
				jogoVelha.setColuna(scan.nextInt());
				jogoVelha.fazerJogada();
			}

			jogoVelha.verificarVencedorLinha();

			jogoVelha.verificarVencedorColuna();

			jogoVelha.verificarVencedorDiagonalPrincipal();

			jogoVelha.verificarVencedorDiagonalSecundaria();
			
			jogoVelha.verificarVelha();

			jogoVelha.mudarJogador();

			jogoVelha.setJogadaValida(false);
		}

	}
}
