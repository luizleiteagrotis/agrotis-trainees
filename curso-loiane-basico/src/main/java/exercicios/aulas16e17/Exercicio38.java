
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio38{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Salário inicial do funcionário: ");
		
		double salario = scan.nextDouble();
		double aumento = 1.5 / 100;
		
		System.out.println("Ano atual:");
		
		int ano = scan.nextInt();
		
		for(int i = 0; i < ano - 1996; i++) {
			
			salario = salario * (1 + aumento);
			aumento = aumento * 2;
		}
		
		System.out.println("Salário atual: R$" + String.format("%.2f", salario));
		
		scan.close();
	}
}
