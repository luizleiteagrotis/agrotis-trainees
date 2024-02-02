
package exercicios.aulas14a17;

import java.util.Scanner;

class Exercicio10{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Digite o turno que estuda (M - matutino; V - vespertino; N - noturno.):");
		
		char turno = scan.next().toLowerCase().charAt(0);	
		
		if(turno == 'm') {
			System.out.println("Bom Dia!");
		} else if(turno == 'v') {
			System.out.println("Boa Tarde!");
		} else if(turno == 'n'){
			System.out.println("Boa Noite!");
		} else {
			System.out.println("Valor Inválido!");
		}
	}
}