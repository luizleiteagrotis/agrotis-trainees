
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio7{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Digite 5 números inteiros:");
		
		int numArray[] = new int[5];
		
		for(int i = 0; i < numArray.length; i++) {
			
			numArray[i] = scan.nextInt();
			
		}
		
		int maior = 0;
		
		for(int i = 0; i < numArray.length; i++) {
			
			if(numArray[i] > maior) {
				maior = numArray[i];
			}
			
		}
		
		System.out.println(maior);
		
	}
}
