
package exercicios.aulas14a17;

import java.util.Scanner;

class Exercicio20{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Insira suas notas:");
		
		int primeiraNota = scan.nextInt();
		int segundaNota = scan.nextInt();
		int terceiraNota = scan.nextInt();
		
		double media = (primeiraNota + segundaNota) / 2;
		
		if(media == 10) {
			System.out.println("Aprovado com Distinção" + " - Média: " + media);
		} else if(media < 7) {
			System.out.println("Reprovado" + " - Média: " + media);
		} else {
			System.out.println("Aprovado" + " - Média: " + media);
		}
	}
}