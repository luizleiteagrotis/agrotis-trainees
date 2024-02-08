
package exercicios.aula20;

import java.util.Scanner;

class Exercicio3{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		int[][] A = new int[3][3];
		
		int quantPares = 0;
		int quantImpares = 0;
		
		System.out.println("Preencha a Matriz A: ");
		for(int i = 0; i< A.length; i++) {
			System.out.print("Linha " + i + ": ");
			for(int j = 0; j < A[i].length; j++) {
				A[i][j] = scan.nextInt();
			}
		}
		
		System.out.println("Matriz A: ");
		for(int i = 0; i< A.length; i++) {
			for(int j = 0; j < A[i].length; j++) {
				System.out.print(A[i][j] + " ");
				if(A[i][j] % 2 == 0) {
					quantPares++;
				} else {
					quantImpares++;
				}
			}
			System.out.println("");
		}
		
		System.out.println("Quantidade de pares: " + quantPares + " Quantidade de ímpares: " + quantImpares);
		
	}
}
