
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio39{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);

		int i = 0;
		
		double maiorAltura = Double.MIN_VALUE;
		double menorAltura = Double.MAX_VALUE;
	
		int codigoMaiorAltura = 0;
		int codigoMenorAltura = 0;
		
		while(i < 10) {
			
			System.out.println("Informe o código do aluno: ");
			int codigo = scan.nextInt();
			
			System.out.println("Altura do aluno: ");
			double altura = scan.nextDouble();
			
			if(altura > maiorAltura) {
				maiorAltura = altura;
				codigoMaiorAltura = codigo;
			}
			
			if(altura < menorAltura) {
				menorAltura = altura;
				codigoMenorAltura = codigo;
			}
			
		}
		
		scan.close();
	}
}
