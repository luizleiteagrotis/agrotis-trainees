
package exercicios.aula19;

import java.util.Scanner;

class Exercicio40{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		int[] A = new int[10];
		
		int x = 0;
		
		System.out.print("Vetor A: ");
		for(int i = 0; i< A.length; i++) {
			A[i] = (int) Math.round(Math.random() * 10);
			System.out.print(A[i] + " ");
		}
		
		System.out.println("\nInsira o elemento que deseja buscar:");
		x = scan.nextInt();
		
		for(int i = 0; i < A.length; i++) {
			if(A[i] == x) {
				System.out.println("Elemento encontrado no índice " + i);
				System.exit(0);;
			}
		}
		
		System.out.println("Elemento não encontrado.");
	}
	
}
