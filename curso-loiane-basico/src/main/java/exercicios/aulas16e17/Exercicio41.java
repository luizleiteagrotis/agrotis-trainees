
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio41{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);

		int[] parcelas = {1,3,6,9,12};
		int[] juros = {0,10,15,20,25};
		
		System.out.println("Insira o valor da dívida: ");
		
		double divida = scan.nextDouble();
		
		System.out.println("Valor da Dívida\t Valor dos Juros Quantidade de Parcelas\t Valor da Parcela");
		
		for(int i = 0; i < parcelas.length; i++) {
			double valorParcela = (divida*(1+(juros[i]/100)))/parcelas[i];
			double valorJuros = divida * juros[i]/100;
			System.out.println( "R$ " + String.format("%.2f", divida + valorJuros) + "\t\t" + valorJuros + "\t\t" + parcelas[i] + "\t\t R$ " + String.format("%.2f", valorParcela));
		}
		
		scan.close();
	}
}
