
package exercicios.aula19;

import java.util.Scanner;

class Exercicio21{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		int[] A = new int[10];
		int uns = 0;
		int zeros = 0;
		
		System.out.println("Vetor A: ");
		for(int i = 0; i< A.length; i++) {
			A[i] = (int) Math.round(Math.random() * 1);
		}
		
		for(int i = 0; i< A.length; i++) {
			System.out.print(A[i] + " ");
			if(A[i] == 1) {
				uns++;
			} else {
				zeros++;
			}

		}
		
		int size = A.length;
		double percentualUm = (double) uns/ (double) size * 100;
		double percentualZero = (double) zeros/ (double) size * 100;
		
 		System.out.println("\nPercentual de uns: " + percentualUm + "%");
		System.out.println("Percentual de zeros: " + percentualZero + "%");
		
	}
}
