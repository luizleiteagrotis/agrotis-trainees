
package exercicios.aula19;

import java.util.Scanner;

class Exercicio27{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		int[] A = new int[10];
		
		boolean isPalindromo = true;

		System.out.println("Vetor A: ");
		
		for(int i = 0; i< A.length; i++) {
			A[i] = (int) Math.round(Math.random() * 10);
			System.out.print(A[i] + " ");
		}
		
		int[] B = new int [10];
		int j = A.length - 1;
		
		System.out.println("\nVetor B: ");
		for(int i = 0; i< B.length; i++) {
			B[i] = A[j];
			j--;
			System.out.print(B[i] + " ");
		}
		
	}
}
