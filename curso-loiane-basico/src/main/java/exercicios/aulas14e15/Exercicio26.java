
package exercicios.aulas14e15;

import java.util.Scanner;

class Exercicio26{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Deseja abastecer com gasolina ou álcool?(G ou A)");
		
		char combustivel = scan.next().toUpperCase().charAt(0);
		
		System.out.println("Quantos litros?");
		
		int quantidade = scan.nextInt();
		
		double preco = 0;
		
		if(combustivel == 'A') {
			
			if(quantidade <= 20) {
				
				preco = quantidade * (1.9*0.97);
				
			} else {
				
				preco = quantidade * (1.9*0.95);
				
			}
			
		} else if(combustivel == 'G') {
			
			if(quantidade <= 20) {
				
				preco = quantidade * (2.5*0.96);
				
			} else {
				
				preco = quantidade * (2.5*0.94);
				
			}
			
		} else {
			System.out.println("Opção inválida!");
		}
		
		System.out.println("Valor: R$" + String.format("%.2f", preco));
	}
}
