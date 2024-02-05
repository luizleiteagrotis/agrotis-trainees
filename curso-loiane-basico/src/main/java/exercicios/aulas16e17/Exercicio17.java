
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio17{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Digite um número inteiro:");
		
		int n = scan.nextInt();
		
		System.out.println( n + "! = " + fatorial(n) );
			
		scan.close();
		
	}

	private static int fatorial(int n) {
		if(n <= 1) {
			return n;
		}
		
		return n*fatorial(n-1);
	}
	
}
