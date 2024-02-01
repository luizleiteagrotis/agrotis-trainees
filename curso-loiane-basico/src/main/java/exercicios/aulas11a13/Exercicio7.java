package exercicios.aulas11a13;

import java.util.Scanner;

class Exercicio7{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Digite o tamanho do lado do quadrado (em cm):");
		
		double lado = scan.nextDouble();
		double area = lado * lado;
		
		System.out.println("Dobro da área do quadrado = " + String.format("%.2f", area*2) + "cm²" );
		
	}
}