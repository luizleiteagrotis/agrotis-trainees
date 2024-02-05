
package exercicios.aulas14e15;

import java.util.Scanner;

class Exercicio16{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Digite as três constantes da equação de segundo grau da forma ax² + bx + c:");
		
		System.out.println("a : ");
		double a = scan.nextDouble();
		if(a == 0) {
			System.out.println("Valor inválido!");
			System.exit(0);
		}
		System.out.println("b : ");
		double b = scan.nextDouble();
		System.out.println("c : ");
		double c = scan.nextDouble();
		
		double delta = (Math.sqrt((b * b) - 4 * a * c));
		if(delta < 0) {
			System.out.println("A equação não possui raízes reais");
			System.exit(0);
		}
		
		double x1 = (- b - delta / 2 * a);
		double x2 = (- b + delta / 2 * a);
		
		System.out.println("Raízes :" + String.format("%.2f", x1) + " ; " + String.format("%.2f", x2));
	}
}