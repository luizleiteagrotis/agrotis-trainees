package exercicios.aulas11a13;

import java.util.Scanner;

class Exercicio8{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Valor recebido pela hora:");
		
		double valorHora = scan.nextDouble();
		
		System.out.println("Horas trabalhadas:");
		
		int horas = scan.nextInt();
		
		System.out.println("Salário = R$" + String.format("%.2f", horas*valorHora));
		
	}
}