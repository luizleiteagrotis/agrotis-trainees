
package exercicios.aulas14a17;

import java.util.Scanner;

class Exercicio6{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Insira três numeros inteiros:");
		
		int num1 = scan.nextInt();
		int num2 = scan.nextInt();
		int num3 = scan.nextInt();
		
		int maior = num1 > num2 ? num1 : num2;
		maior = maior > num3 ? maior : num3;
		
		System.out.println("Maior entre os três : " + maior);
	}
}