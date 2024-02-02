package exercicios.aulas11a13;

import java.util.Scanner;

class Exercicio17{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Área a ser pintada (em m²):");
		
		double area = scan.nextDouble();
		
		double litros = area / 6;
		double quantLataGrande = litros / 18;
		double quantLataPequena = litros / 3.6;
		
		System.out.println("Valor apenas latas 18 litros : R$" + String.format("%.2f",quantLataGrande * 80));
		System.out.println("Valor apenas latas 3,6 litros : R$" + String.format("%.2f",quantLataPequena* 25) );
		
		litros = (area * 0.1) /6;
		quantLataGrande =  litros / 18;
		double resto = litros - litros / 18;
		quantLataPequena = resto / 3.6;
		
		double total = Math.ceil(quantLataPequena) * 25 +  Math.ceil(quantLataGrande) * 80;
		
		System.out.println("Menor valor : R$" + total );
	}
}