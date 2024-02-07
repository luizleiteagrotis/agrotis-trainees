
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio24{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Quantas a notas serão inseridas? ");
		
		int n = scan.nextInt();
		int soma = 0;
		
		System.out.println("Insira suas notas: ");
		for(int i = 0; i < n; i++) {
			soma += scan.nextInt();
		}
		
		double media = soma/n;
		
		System.out.println("Média:" + media);
		
		scan.close();
		
	}
}
