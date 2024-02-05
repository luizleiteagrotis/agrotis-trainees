
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio3{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		String nome = "";

		do {
			System.out.println("Insira seu nome:");
		
			nome = scan.next();
		
			if(nome.length() <= 2) {
				System.out.println("O nome deve ter mais que 3 caracteres!");
			}
			
		}while(nome.length() <= 2);
		
		int idade = 0;

		do {
			System.out.println("Insira sua idade:");
		
			idade = scan.nextInt();
		
			if(idade < 0 || idade > 150) {
				System.out.println("Idade inválida!");
			}
			
		}while(idade < 0 || idade > 150);
		
		double salario = 0;

		do {
			System.out.println("Insira seu salario:");
		
			salario = scan.nextDouble();
		
			if(salario <= 0 ) {
				System.out.println("Salário inválido!");
			}
			
		}while(salario <= 0);
		
		char sexo = 'n';

		do {
			System.out.println("Insira seu sexo:");
		
			sexo = scan.next().toUpperCase().charAt(0);
		
			if(sexo != 'F' && sexo != 'M' ) {
				System.out.println("Sexo inválido!");
			}
			
		}while(sexo != 'F' && sexo != 'M');
		
		char estadoCivil = 'n';

		do {
			System.out.println("Insira seu estado civil:");
		
			estadoCivil = scan.next().toUpperCase().charAt(0);
		
			if(estadoCivil != 'S' && estadoCivil != 'C' && estadoCivil != 'V' && estadoCivil != 'D') {
				System.out.println("Estado Civil inválido!");
			}
			
		}while(estadoCivil != 'S' && estadoCivil != 'C' && estadoCivil != 'V' && estadoCivil != 'D');
		
		
		scan.close();
	}
}
