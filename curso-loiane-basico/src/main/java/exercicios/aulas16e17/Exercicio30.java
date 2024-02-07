
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio30{


	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Preço do pão:");
		
		double valorPao = scan.nextDouble();
		
		System.out.println("Panificadora Pão de Ontem - Tabela de preços ");
		
		for(int i = 0; i < 50; i++) {
			
			System.out.println((i+1) + "\t-\tR$" + String.format("%.2f",(i+1)*valorPao));
				
		}

	}
}
