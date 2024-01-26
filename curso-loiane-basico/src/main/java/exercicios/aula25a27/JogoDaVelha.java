package exercicios.aula25a27;

import java.util.Scanner;

public class JogoDaVelha {
	
	 private Character[][] tabuleiro;
	    private final char JOGADOR_A = 'X';
	    private final char JOGADOR_B = 'O';

	    public JogoDaVelha() {
	        tabuleiro = new Character[3][3];
	        for (int i = 0; i < tabuleiro.length; i++) {
	            for (int j = 0; j < tabuleiro[i].length; j++) {
	                tabuleiro[i][j] = ' ';
	            }
	        }
	    }

	    public void jogar() {
	        Scanner sc = new Scanner(System.in);
	        int qtnJogadas = 0;

	        boolean flagChecagemVitoria = true;
	        boolean jogadaCorreta;
	 
	    }

	    private void mostraTabuleiro() {
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

	    public boolean verificaVitoria(char jogador) {
	        for (int i = 0; i < 3; i++) {
	            if ((tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador) ||
	                (tabuleiro[0][i] == jogador && tabuleiro[1][i] == jogador && tabuleiro[2][i] == jogador)) {
	                return true;
	            }
	        }

	        if ((tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) ||
	            (tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador)) {
	            return true;
	        }

	        return false;
	    }

	    public static void main(String[] args) {
	        JogoDaVelha jogo = new JogoDaVelha();
	        jogo.jogar();
	    }
	}


