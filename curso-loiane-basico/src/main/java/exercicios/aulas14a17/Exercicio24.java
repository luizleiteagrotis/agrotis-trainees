
package exercicios.aulas14a17;

import java.util.Scanner;

class Exercicio24{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Insira dois números:");
		
		double x = scan.nextDouble();
		double y = scan.nextDouble();
		
		System.out.println("Que operação deseja realizar?");
		
		char op = scan.next().charAt(0);
		
		double resultado = 0;
		
		switch(op) {
		case '+': resultado = x+y; break;
		case '-': resultado = x-y; break;
		case '/': resultado = x/y; break;
		case '*': resultado = x*y; break;
		case '%': resultado = x%y; break;
		default: System.out.println("Operação inválida!"); break;
		}
		
		System.out.println("Resultado = " + String.format("%.2f", resultado) + (resultado%2 == 0 ? "; Par;" : "; Ímpar;") + 
				(resultado > 0 ? " Positivo;" : " Negativo;") + 
				(resultado == Math.floor(resultado) ? " Inteiro.": " Decimal."));
	}
}
