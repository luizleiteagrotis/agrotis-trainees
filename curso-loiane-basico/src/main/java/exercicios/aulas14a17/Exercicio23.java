
package exercicios.aulas14a17;

import java.util.Scanner;

class Exercicio23{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Insira um numero:");
		
		double num = scan.nextDouble();
	
		if(num == Math.floor(num)) {
			System.out.println("Valor inteiro");
		} else {
			System.out.println("Valor decimal");
		}
		
	}
}
