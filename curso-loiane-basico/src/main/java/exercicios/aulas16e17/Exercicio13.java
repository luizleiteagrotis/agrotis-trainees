
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio13{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Insira a base: ");
		
		int base = scan.nextInt();
		
		System.out.println("Insira a potencia: ");
		
		int potencia = scan.nextInt();
		
		int resultado = 1;
		
		for(int i = 0; i < potencia; i++) {
			
			resultado = resultado * base;
			
		}
		
		System.out.println(resultado);
		
		scan.close();
	}
}
