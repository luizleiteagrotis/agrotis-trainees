
package exercicios.aulas14e15;

import java.util.Scanner;

class Exercicio27{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Quantos kilos de morango deseja comprar?");
		
		int quantMorango = scan.nextInt();
		
		System.out.println("Quantos kilos de maçã deseja comprar?");
		
		int quantMaca = scan.nextInt();
		
		double preco = 0;
		
		if(quantMorango <= 5) {
			
			preco += quantMorango * 2.5;
			
		} else {
			
			preco += quantMorango * 2.2;
		}
		
		if(quantMaca <= 5) {
			
			preco += quantMaca * 1.8;
			
		} else {
			
			preco += quantMaca * 1.5;
			
		}
		
		if(quantMorango + quantMaca > 8 || preco >= 25) {
			preco = preco * 0.9;
		}
		
		
		System.out.println("Valor: R$" + String.format("%.2f", preco));
	}
}
