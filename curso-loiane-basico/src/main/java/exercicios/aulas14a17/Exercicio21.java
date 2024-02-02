
package exercicios.aulas14a17;

import java.util.Scanner;

class Exercicio21{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Quantia a sacar:");
		
		int valorSaque = scan.nextInt();
		
		if(valorSaque < 10 || valorSaque > 600) {
			System.out.println("Valor inválido! Insira um valor entre 10 e 600 reais.");
			System.exit(0);
		}
		
		int notasCem = valorSaque / 100;
		valorSaque -= notasCem * 100;
		
		int notasCinquenta = valorSaque / 50;
		valorSaque -= notasCinquenta * 50;
		
		int notasDez = valorSaque / 10;
		valorSaque -= notasDez / 10;
		
		int notasCinco = valorSaque / 5;
		valorSaque -= notasCinco * 5;
		
		int notasUm = valorSaque;
		
		System.out.println(notasCem + (notasCem > 1 ? " notas de 100; " : " nota de 100; " ) + 
				notasCinquenta + (notasCinquenta > 1 ? " notas de 50; " : " nota de 50; " ) + 
				notasDez + (notasDez > 1 ? " notas de 10; " : " nota de 10; " ) + 
				notasCinco + (notasCinco > 1 ? " notas de 5; " : " nota de 5; " ) + 
				notasUm + (notasUm > 1 ? " notas de 1." : " nota de 1." ));
	}
}