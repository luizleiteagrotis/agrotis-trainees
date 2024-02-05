
package exercicios.aulas14e15;

import java.util.Scanner;

class Exercicio8{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Insira o preço de três produtos:");
		
		double num1 = scan.nextDouble();
		double num2 = scan.nextDouble();
		double num3 = scan.nextDouble();
	
		
		double menor = num1 < num2 ? num1 : num2;
		menor = menor < num3 ? menor : num3;
		
		System.out.println("Produto a comprar : " + String.format("%.2f", menor));
	}
}