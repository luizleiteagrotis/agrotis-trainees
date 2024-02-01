package exercicios.aulas11a13;

import java.util.Scanner;

class Exercicio6{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Digite o raio do círculo (em cm):");
		
		double raio = scan.nextDouble();
		double area = raio*raio*Math.PI;
		
		System.out.println("Área do círculo = " + String.format("%.2f", area) + "cm²" );
		
	}
}