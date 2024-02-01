package exercicios.aulas11a13;

import java.util.Scanner;

class Exercicio14 {
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Peso do Peixe (kg):");
		
		int peso = scan.nextInt();
		int excesso = 0;
		double multa = 0;
		
		if(peso > 50) {
			excesso = peso - 50;
			multa = excesso * 4;
		}
		
		System.out.println("Quilos Excedentes = " + excesso + "kg" + " Multa = R$" + String.format("%.2f", multa) );
		
	}
}