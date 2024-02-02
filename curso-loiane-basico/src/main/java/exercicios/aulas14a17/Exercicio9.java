
package exercicios.aulas14a17;

import java.util.Scanner;

class Exercicio9{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Insira três numeros : ");
		
		double num1 = scan.nextDouble();
		double num2 = scan.nextDouble();
		double num3 = scan.nextDouble();
		
	
		if(num1 < num2) {
			double temp = num2;
			num2 = num1;
			num1 = temp;
		}
		
		if(num2 < num3) {
			double temp = num3;
			num3 = num2;
			num2 = temp;
		}
		
		if(num1 < num2) {
			double temp = num2;
			num2 = num1;
			num1 = temp;
		}
		
		System.out.println(String.format("%.2f", num1) + " " + String.format("%.2f", num2) + " "  + String.format("%.2f", num3));
	}
	
}