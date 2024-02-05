
package exercicios.aulas14e15;

import java.util.Scanner;

class Exercicio14{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Insira suas notas:");
		
		int primeiraNota = scan.nextInt();
		int segundaNota = scan.nextInt();
		
		double media = (primeiraNota + segundaNota) / 2;
		String situacao = "";
		char conceito = 'E';
		
		if(media < 4) {
			conceito = 'E';
			situacao = "REPROVADO";
		} else if(media < 6) {
			conceito = 'D';
			situacao = "REPROVADO";
		} else if(media < 7.5){
			conceito = 'C';
			situacao = "APROVADO";
		} else if(media < 9){
			conceito = 'B';
			situacao = "APROVADO";
		} else {
			conceito = 'A';
			situacao = "APROVADO";
		}
		
		System.out.println("Média: " + media + "\t Conceito: " + conceito);
		System.out.println("Situação: " + situacao);
	}
}