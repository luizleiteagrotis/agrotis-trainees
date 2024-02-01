package exercicios.aulas11a13;

import java.util.Scanner;

class Exercicio3{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Digite dois números:");
		
		int x = scan.nextInt();
		int y = scan.nextInt();
		int sum = x+y;
		
		System.out.println("Soma = " + sum);
		
	}
}