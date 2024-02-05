
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio5{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		double populacaoA = 0;
		double populacaoB = 0;
		double taxaCrescimentoA = 0;
		double taxaCrescimentoB = 0;
		
		do {
			
			System.out.println("Insira a população do país A:");
			populacaoA = scan.nextDouble();
			
			if(populacaoA <= 0) {
				
				System.out.println("Insira a população do país A:");
				
			}
			
		}while(populacaoA <= 0);
		
		
		
		System.out.println("Insira a taxa de crescimento do país A:");
		
		 taxaCrescimentoA = scan.nextDouble()/100;
		
		System.out.println("Insira a população do país B:");
		
		 populacaoB = scan.nextDouble();
		
		System.out.println("Insira a taxa de crescimento do país B:");
		
		 taxaCrescimentoB = scan.nextDouble()/100;
		
		int anos = 0;
		
		while(populacaoA < populacaoB) {
			
			populacaoA = populacaoA * (1 + taxaCrescimentoA);
			populacaoB = populacaoB * (1 +taxaCrescimentoB);
			anos ++;
			
		}
		
		System.out.println("Anos necessários: " + anos);
	}
}