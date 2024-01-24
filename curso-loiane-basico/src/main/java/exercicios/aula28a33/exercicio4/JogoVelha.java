package exercicios.aula28a33.exercicio4;

public class JogoVelha {

	private String jogador;
	private String[][] tabuleiro = new String[3][3];
	private boolean vencedor = false;
	private boolean jogadaValida = false;
	private int linha;
	private int coluna;
	private int qtnJogadas;
	private int camposIguais;

	public JogoVelha() {
		super();
	}

	public JogoVelha(String jogador, String[][] tabuleiro, boolean vencedor, boolean jogadaValida, int linha,
			int coluna, int qtnJogadas, int camposIguais) {
		super();
		this.jogador = jogador;
		this.tabuleiro = tabuleiro;
		this.vencedor = vencedor;
		this.jogadaValida = jogadaValida;
		this.linha = linha;
		this.coluna = coluna;
		this.qtnJogadas = qtnJogadas;
		this.camposIguais = camposIguais;
	}

	public String[][] getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(String[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public boolean getJogadaValida() {
		return jogadaValida;
	}

	public void setJogadaValida(boolean jogadaValida) {
		this.jogadaValida = jogadaValida;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public boolean getVencedor() {
		return vencedor;
	}

	public String getJogador() {
		return jogador;
	}

	public void setJogador(String jogador) {
		this.jogador = jogador;
	}

	public void setCamposIguais(int camposIguais) {
		this.camposIguais = camposIguais;
	}

	public void mostrarTabuleiro() {
		System.out.println(" -- " + jogador + " -- ");
		System.out.println();
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				System.out.print("[" + tabuleiro[i][j] + "] ");
			}
			System.out.println();
		}
	}

	public boolean fazerJogada() {
		if (tabuleiro[linha - 1][coluna - 1] != " ") {
			System.out.println("Esse espaço já está ocupado");
			mostrarTabuleiro();
			return false;
		} else {
			tabuleiro[linha - 1][coluna - 1] = jogador;
			jogadaValida = true;
			qtnJogadas++;
			mostrarTabuleiro();
			return true;
		}
	}

	public void verificarVencedorLinha() {
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				if (tabuleiro[i][j].equals(jogador)) {
					camposIguais++;
					if (camposIguais == 3) {
						System.out.println("Temos um vencedor de linha " + jogador);
						mostrarTabuleiro();
						vencedor = true;
					}
				}
			}
			camposIguais = 0;
		}
	}

	public void verificarVencedorColuna() {
		for (int j = 0; j < 3; j++) {
			for (int i = 0; i < tabuleiro.length; i++) {
				if (tabuleiro[i][j].equals(jogador)) {
					camposIguais++;
					if (camposIguais == 3) {
						System.out.println("Temos um vencedor de coluna " + jogador);
						mostrarTabuleiro();
						vencedor = true;
					}
				}
			}
			camposIguais = 0;
		}
	}

	public void verificarVencedorDiagonalPrincipal() {
		for (int i = 0; i < tabuleiro.length; i++) {
			if (tabuleiro[i][i].equals(jogador)) {
				camposIguais++;
				if (camposIguais == 3) {
					System.out.println("Temos um vencedor de diagonal principal " + jogador);
					mostrarTabuleiro();
					vencedor = true;
				}
			}
		}
		camposIguais = 0;
	}

	public void verificarVencedorDiagonalSecundaria() {
		for (int i = 0, j = 2; i < tabuleiro.length; i++, j--) {
			if (tabuleiro[i][j].equals(jogador)) {
				camposIguais++;
				if (camposIguais == 3) {
					System.out.println("Temos um vencedor de diagonal secundária " + jogador);
					mostrarTabuleiro();
					vencedor = true;
				}
			}
		}
		camposIguais = 0;
	}

	public void verificarVelha() {
		if (qtnJogadas == 9) {
			System.out.println("Deu velha");
			mostrarTabuleiro();
			vencedor = true;
		}
	}

	public void mudarJogador() {
		if (jogador.equalsIgnoreCase("X")) {
			jogador = "O";
		} else {
			jogador = "X";
		}
	}

}
