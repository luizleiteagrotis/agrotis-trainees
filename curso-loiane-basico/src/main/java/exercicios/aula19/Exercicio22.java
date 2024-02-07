
package exercicios.aula19;

import java.util.Scanner;

class Exercicio22{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		int[] A = new int[10];
		
		boolean todosPares = true;

		System.out.println("Vetor A: ");
		
		for(int i = 0; i< A.length; i++) {
			A[i] = (int) Math.round(Math.random() * 10);
			System.out.print(A[i] + " ");
		}
		
		for(int i = 0; i< A.length; i++) {
			if(A[i]%2 != 0) {
				todosPares = false;
				break;
			} 
		}

		if(todosPares == false) {
			System.out.println("\nProcesso encerrado!");
		}else {
			System.out.println("\nTodos os elementos do vetor são pares!");
		}
		
	}
}
