package exercicios.aulas11a13;

import java.util.Scanner;

class Exercicio5{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Digite o valor em metros:");
		
		double metros = scan.nextDouble();
		
		System.out.println("Valor em centímetros =  " + metros*100 + "cm" );
		
	}
}