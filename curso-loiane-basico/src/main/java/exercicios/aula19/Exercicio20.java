
package exercicios.aula19;

import java.util.Scanner;

class Exercicio20{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		double[] A = new double[20];

		System.out.println("Cotação do dólar: ");
		
		double cotacaoDolar = scan.nextDouble();
		
		for(int i = 0; i< A.length; i++) {
			A[i] = cotacaoDolar * i;
			System.out.print(" R$ " + String.format("%.2f", A[i]));
		}
		
	}
}
