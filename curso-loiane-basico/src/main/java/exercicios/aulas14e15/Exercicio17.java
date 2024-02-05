
package exercicios.aulas14e15;

import java.util.Scanner;

class Exercicio17{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Digite um ano:");
		
		int ano = scan.nextInt();
		
		if(ano % 4 != 0) {
			System.out.println("Esse ano não é bissexto!");
			System.exit(0);
		}
		
		if(ano % 100 == 0) {
			System.out.println("Esse ano não é bissexto!");
			System.exit(0);
		}
		
		if(ano % 400 == 0) {
			System.out.println("Esse ano não é bissexto!");
			System.exit(0);
		}
		
		System.out.println("Esse ano é bissexto!");
	}
}