
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio15{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Defina o tamanho da série de Fibonacci:");
		
		int n = scan.nextInt();
		
		
		for(int i = 0; i < n+1; i++) {
			System.out.println("F(" + i + ") = " +fibonacci(i));
		}
		

		scan.close();
		
	}

	private static int fibonacci(int n) {
		if(n <= 1) {
			return n;
		}
		
		return fibonacci(n-1) + fibonacci(n-2);
	}
	
}
