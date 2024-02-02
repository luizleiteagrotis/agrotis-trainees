package exercicios.aulas14a17;

import java.util.Scanner;

class Exercicio1{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Insira dois numeros inteiros:");
		
		int num1 = scan.nextInt();
		int num2 = scan.nextInt();
		
		System.out.println("Maior valor : " + (num1 > num2 ? num1 : num2) );
	}
}