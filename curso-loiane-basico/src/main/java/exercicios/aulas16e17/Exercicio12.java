
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio12{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Insira um número de 1 a 10: ");
		
		int num = scan.nextInt();
		
		System.out.println("Tabuada de " + num + ": ");
		
		for(int i = 1; i <= 10; i++) {
			
			System.out.println(num + " X " + i + " = " + num*i);
			
		}
		
		scan.close();
	}
}
