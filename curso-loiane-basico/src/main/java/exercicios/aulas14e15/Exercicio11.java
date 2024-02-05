
package exercicios.aulas14e15;

import java.util.Scanner;

class Exercicio11{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Salário atual:");
		
		double salario = scan.nextDouble();
		double percentual = 0;
		double salarioReajustado = 0;
	
		if(salario <= 280) {
			percentual = 0.2;
			salarioReajustado = salario * 1.2;
		} else if(salario < 700) {
			percentual = 0.15;
			salarioReajustado = salario * 1.15;
		} else if(salario < 1500) {
			percentual = 0.1;
			salarioReajustado = salario * 1.1;
		} else {
			percentual = 0.05;
			salarioReajustado = salario * 1.05;
		}
		
		System.out.println("Salario original: R$" + String.format("%.2f", salario) + " - Percentual de aumento: " + percentual*100 + "%" + " - Valor do aumento: R$" 
							+ String.format("%.2f",(salarioReajustado - salario)) + " - Salário Reajustado: R$" + String.format("%.2f", salarioReajustado));
	}
}