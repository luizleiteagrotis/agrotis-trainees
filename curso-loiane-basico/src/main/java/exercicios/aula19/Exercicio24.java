
package exercicios.aula19;

import java.util.Scanner;

class Exercicio24{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		int[] A = new int[10];
		
		boolean isPalindromo = true;

		System.out.println("Vetor A: ");
		
		for(int i = 0; i< A.length; i++) {
			A[i] = (int) Math.round(Math.random() * 10);
			System.out.print(A[i] + " ");
		}
		
		System.out.println("\nVetor B: ");
		int[] B = new int [10];
		for(int i = 0; i< B.length; i++) {
			if(A[i]%2 == 0) {
				B[i] = 1;
			}else {
				B[i] = 0;
			}
			System.out.print(B[i] + " ");
		}

	}
}
