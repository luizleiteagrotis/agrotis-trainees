
package exercicios.aulas14e15;

import java.util.Scanner;

class Exercicio7{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Insira três numeros inteiros:");
		
		int num1 = scan.nextInt();
		int num2 = scan.nextInt();
		int num3 = scan.nextInt();
		
		int maior = num1 > num2 ? num1 : num2;
		maior = maior > num3 ? maior : num3;
		
		System.out.println("Maior entre os três : " + maior);
		
		int menor = num1 < num2 ? num1 : num2;
		menor = menor < num3 ? menor : num3;
		
		System.out.println("Menor entre os três : " + menor);
	}
}