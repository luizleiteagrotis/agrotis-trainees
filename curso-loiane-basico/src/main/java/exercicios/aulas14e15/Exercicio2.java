package exercicios.aulas14e15;

import java.util.Scanner;

class Exercicio2{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Insira um numero inteiro:");
		
		int num = scan.nextInt();
		
		if(num > 0 ) {
			System.out.println("Positivo");
		} else if(num < 0 ) {
			System.out.println("Negativo");
		}
	}
}