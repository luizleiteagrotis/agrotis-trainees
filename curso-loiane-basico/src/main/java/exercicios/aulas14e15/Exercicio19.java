
package exercicios.aulas14e15;

import java.util.Scanner;

class Exercicio19{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
			
		System.out.println("Digite um número inteiro menor que 1000");
		
		int valor = scan.nextInt();
		
		if(valor >= 1000) {
			System.out.println("Valor inválido!");
			System.exit(0);
		}
		
		int resto = valor % 100;
		int centenas = (valor - resto)/100;
		
		valor = resto;
		resto = resto % 10;

		int dezenas = (valor - resto)/10;
		int unidades = resto;
		
		System.out.println(centenas + " centenas," + " " + dezenas  + " dezenas," + " " + unidades  + " unidades");
	}
}