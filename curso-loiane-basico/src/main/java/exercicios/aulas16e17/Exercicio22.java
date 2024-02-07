
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio22{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		int n = 0;
		
		System.out.println("Digite um número inteiro:");
		
		n = scan.nextInt();
		
		if(isPrime(n)) {
			System.out.println("Primo");
		} else {
			System.out.println("Não Primo");
			System.out.println("Divisores:");
			for(int i = 1; i <= n; i++) {
				if((n%i) == 0) {
					System.out.print(i + " ");
				}
			}
		}
			
		scan.close();
		
	}

	private static boolean isPrime(int n) {
		if( n <= 3) {
			return true;
		}
		
		for(int i = 2; i <= n/2; i++) {
			if((n%i) == 0) {
				return false;
			}
		}
		return true;
		
	}
}
