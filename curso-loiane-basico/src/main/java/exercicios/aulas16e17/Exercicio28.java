
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio28{


	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Quantos CDs tem em sua coleção?");
		
		int totalCDs = scan.nextInt();
		
		double valorTotal = 0;
		
		for(int i = 0; i < totalCDs; i++) {
			
			System.out.println("Qual o valor gasto no CD " + i + "?");
			double valorCD = scan.nextDouble();
			valorTotal += valorCD; 
		}
		
		double valorMedio = valorTotal/totalCDs;
		
		System.out.println("Valor total investido: R$" + String.format("%.2f", valorTotal) + " Valor médio de cada CD: R$" + String.format("%.2f", valorMedio));
		
		scan.close();
		
	}
}
