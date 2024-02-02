
package exercicios.aulas14a17;

import java.util.Scanner;

class Exercicio22{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Insira um numero inteiro:");
		
		int num = scan.nextInt();
	
		if(num%2 == 0) {
			System.out.println("Número par");
		} else {
			System.out.println("Número ímpar");
		}
		
	}
}
