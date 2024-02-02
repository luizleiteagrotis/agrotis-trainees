package exercicios.aulas11a13;

import java.util.Scanner;

class Exercicio16{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Área a ser pintada (em m²):");
		
		double area = scan.nextDouble();
		
		double litros = area / 6;
		double quantLata = litros / 18;
		
		System.out.println("Quantidade de latas : " + String.format("%.2f",quantLata) + " Valor apenas latas 18 litros : R$" + String.format("%.2f",quantLata * 80) );

	}
}