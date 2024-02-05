
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio1{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		int nota = 0;
		
		do {
			
			System.out.println("Insira uma nota de 0 a 10:");
			nota = scan.nextInt();
			if(nota < 0 || nota > 10) {
				System.out.println("Valor inválido!");
			}
			
		} while(nota < 0 || nota > 10);
	}
}
