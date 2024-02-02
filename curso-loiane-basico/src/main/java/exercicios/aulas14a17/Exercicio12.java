
package exercicios.aulas14a17;

import java.util.Scanner;

class Exercicio12{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Valor da hora trabalhada:");
		
		double valorHora = scan.nextDouble();
		
		System.out.println("Horas trabalhadas esse mês:");
		
		int horas = scan.nextInt();

		double impostoRenda = 0;
		double salarioBruto = valorHora * horas;
	
		if(salarioBruto > 900 && salarioBruto < 1500 ) {
			impostoRenda = 0.05;
		} else if(salarioBruto < 2500) {
			impostoRenda = 0.1;
		} else {
			impostoRenda = 0.2;	
		}
		
		double descontos = (salarioBruto * impostoRenda) + (salarioBruto * 0.1) + (salarioBruto * 0.11);
		
		System.out.println("Salário Bruto: (" + String.format("%.2f", valorHora) + " * " + horas + ")\t: R$" + String.format("%.2f", salarioBruto));
		System.out.println("(-) IR (" + (impostoRenda * 100) + "%)\t\t\t: R$" + String.format("%.2f", salarioBruto * impostoRenda));
		System.out.println("(-) INSS (10%)\t\t\t: R$" + String.format("%.2f", salarioBruto * 0.1));
		System.out.println("FGTS (11%)\t\t\t: R$" + String.format("%.2f", salarioBruto * 0.11));
		System.out.println("Total de descontos\t\t: R$" + String.format("%.2f", descontos));
		System.out.println("Salário Líquido\t\t\t: R$" + String.format("%.2f", salarioBruto - descontos));
		
	}
}