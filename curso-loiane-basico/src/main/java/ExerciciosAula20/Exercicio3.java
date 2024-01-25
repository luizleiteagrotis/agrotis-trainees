package ExerciciosAula20;

import java.util.Scanner;

public class Exercicio3 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner (System.in);
		
		// declarando variaveis
		int qtdPares = 0;
		int qtdImpares = 0;
		
		//Instanciar
		int[][] M = new int[3][3];
		
		M[0][0] = 1;
		M[0][1] = 5;
		M[0][2] = 6;
		
		M[1][0] = 0;
		M[1][1] = 3;
		M[1][2] = 5;
		
		M[2][0] = 3;
		M[2][1] = 4;
		M[2][2] = 3;
		
		for (int i = 0; i<M.length; i++) {
			for (int j=0; j<M[i].length; j++) {
				System.out.print(M[i][j] + " - ");
			}
		System.out.println();
		}

		for (int i=0; i<M.length; i++){
			for (int j=0; j<M[i].length; j++){
	            if (M[i][j] % 2 == 0){
	                qtdPares++;
		        }
			} 
        }
		
		for (int i=0; i<M.length; i++){
			for (int j=0; j<M[i].length; j++){
		        if (M[i][j] % 2 != 0){
		            qtdImpares++;
		        }
			}
		}
		
		System.out.println();
        System.out.println("Quantidade Pares = " + qtdPares);
        System.out.println();
        System.out.println("Quantidade Impares = " + qtdImpares);   
	}
}