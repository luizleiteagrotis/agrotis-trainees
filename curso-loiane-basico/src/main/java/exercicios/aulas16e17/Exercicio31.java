
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio31{


	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			double valorProduto = 0;
			double total = 0;
			int i = 0;
		
			System.out.println("Lojas Tabajara");
		
			do {
			
				System.out.print("Produto " + (i+1) + ": R$ ");
				valorProduto = scan.nextDouble();
				total += valorProduto;
				i++;
		
			}while(valorProduto != 0);
		
			System.out.println("Total: R$ " + String.format("%.2f",total));
		
			System.out.print("Dinheiro: R$ ");
		
			double pagamento = scan.nextDouble();
		
			System.out.println("Troco: R$ " + String.format("%.2f",(pagamento - total)));
			
			System.out.println("\n");
		}
	}
}
