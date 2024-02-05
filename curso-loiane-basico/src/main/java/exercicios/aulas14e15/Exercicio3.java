
package exercicios.aulas14e15;

import java.util.Scanner;

class Exercicio3{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Insira seu sexo:");
		
		char sexo = scan.next().charAt(0);
		
		if(sexo == 'M' || sexo == 'm') {
			
			System.out.println("Masculino");
			
		} else if(sexo == 'F' || sexo == 'f') {
			
			System.out.println("Feminino");
			
		} else {
			
			System.out.println("Sexo inválido");
			
		}
	}
}