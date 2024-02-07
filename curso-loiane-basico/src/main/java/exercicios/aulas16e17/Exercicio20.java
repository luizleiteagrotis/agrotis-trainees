
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio20{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		int n = 0;
		
		
		while (true) {
		
			System.out.println("Digite um número inteiro:");
		
			n = scan.nextInt();
			
			if(n < 0 || n >= 16) {
				break;
			}
		
			System.out.println( n + "! = " + fatorial(n) );
		
		};
		
		System.out.println("Valor inválido!");
			
		scan.close();
		
	}

	private static int fatorial(int n) {
		if(n <= 1) {
			return n;
		}
		
		return n*fatorial(n-1);
	}
	
}
