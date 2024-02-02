
package exercicios.aulas14a17;

import java.util.Scanner;

class Exercicio5{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Insira suas notas:");
		
		int primeiraNota = scan.nextInt();
		int segundaNota = scan.nextInt();
		
		double media = (primeiraNota + segundaNota) / 2;
		
		if(media == 10) {
			System.out.println("Aprovado com Distinção");
		} else if(media < 7) {
			System.out.println("Reprovado");
		} else {
			System.out.println("Aprovado");
		}
	}
}