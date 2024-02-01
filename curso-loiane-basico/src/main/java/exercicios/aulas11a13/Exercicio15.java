package exercicios.aulas11a13;

import java.util.Scanner;

class Exercicio15{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Valor recebido pela hora:");
		
		double valorHora = scan.nextDouble();
		
		System.out.println("Horas trabalhadas:");
		
		int horas = scan.nextInt();
		
		double salarioBruto = valorHora * horas;
		double impostoRenda = salarioBruto * 0.11;
		double inss = salarioBruto * 0.08;
		double sindicato = salarioBruto * 0.05;
		double salarioLiquido = salarioBruto - impostoRenda - inss - sindicato;
		
		System.out.println("+ Salário Bruto : R$" +  String.format("%.2f",salarioBruto) + " - IR (11%) : R$" + String.format("%.2f",impostoRenda) + " - INSS (8%) : R$" +  String.format("%.2f",inss) 
							+ " - Sindicato (5%) : R$" +  String.format("%.2f",sindicato) + " = Salário Líquido : R$ " + String.format("%.2f",salarioLiquido));
		
	}
}