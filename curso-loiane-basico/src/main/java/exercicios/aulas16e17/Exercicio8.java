
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio8{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Digite 5 números inteiros:");
		
		int numArray[] = new int[5];
		
		for(int i = 0; i < numArray.length; i++) {
			
			numArray[i] = scan.nextInt();
			
		}
		
		int soma = 0;
		
		for(int i = 0; i < numArray.length; i++) {
			
			soma += numArray[i];
			
		}
		
		double media = soma / numArray.length;
		
		System.out.println("Soma: " + soma + "\tMédia: " + media);
		
	}
}
