
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio23{
	public static int divisoes;
	
	public static void main (String args[]) {
	
		Scanner scan = new Scanner(System.in);
		
		int n = 0;
		
		System.out.println("Digite um número inteiro:");
		
		n = scan.nextInt();
		
		for(int i = 1; i < n; i++) {
		
			if(isPrime(i)) {
				System.out.println(i + " n° de divisões: " + divisoes);
			} 
			
		}
			
		scan.close();
		
	}

	private static boolean isPrime(int n) {
		divisoes = 0;
		if( n <= 3) {
			return true;
		}
		
		for(int i = 2; i <= n/2; i++) {
			if((n%i) == 0) {
				return false;
			}
			divisoes++;
		}
		return true;
	}
}
