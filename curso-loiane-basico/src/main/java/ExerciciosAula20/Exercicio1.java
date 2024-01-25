package ExerciciosAula20;

import java.util.Scanner;

public class Exercicio1 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner (System.in);
		
		// declarando variaveis
		int linha = 0;
		int coluna = 0;
		int maiorValor = 0;
		//Instanciar
		int[][] M = new int[4][4];
		
		M[0][0] = 1;
		M[0][1] = 5;
		M[0][2] = 6;
		M[0][3] = 7;
		
		M[1][0] = 0;
		M[1][1] = 3;
		M[1][2] = 5;
		M[1][3] = 2;
		
		M[2][0] = 3;
		M[2][1] = 4;
		M[2][2] = 3;
		M[2][3] = 4;
		
		M[3][0] = 9;
		M[3][1] = 5;
		M[3][2] = 7;
		M[3][3] = 1;	
		
		//Mostrando a Matriz 4x4
		for (int i = 0; i<M.length; i++) {
			for (int j=0; j<M[i].length; j++) {
				System.out.print(M[i][j] + " - ");
			}
		System.out.println();
		}
		
		for (int i=0; i<M.length; i++){
			for (int j=0; j<M[i].length; j++){
				if (M[i][j] > maiorValor){
					maiorValor = M[i][j];
	                linha = i;
	                coluna = j;
	            }				
	        }
		}
		System.out.println();
        System.out.println("Posição do valor maior = Coluna " + coluna + " | Linha " + linha);
        System.out.println();
        System.out.println("Valor maior = " + maiorValor);
	       
	}
}