
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio19{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Insira o tamanho do conjunto:");
		
		int n = scan.nextInt();
		
		int numArray[] = new int[n];
		
		int i = 0;
		
		for(i = 0 ;i < numArray.length; i++) {
		
			do {
			
				System.out.println("Insira um número de 0 a 1000:");
			
				numArray[i] = scan.nextInt();
			
				if(numArray[i] > 1000 || numArray[i] < 0) {
					System.out.println("Valor inválido!");
					continue;
				}
		
			}while(numArray[i] > 1000 || numArray[i] < 0);
		}
		
		int maior = 0;
		int menor = Integer.MAX_VALUE;
		int soma = 0;
		
		for(i = 0; i < numArray.length; i++) {
			
			if(numArray[i] > maior) {
				maior = numArray[i];
			}
			
			if(numArray[i] < menor) {
				menor = numArray[i];
			}
			
			soma += numArray[i];
		}
		
		System.out.println("Maior valor do conjunto: " +  maior + " - Menor valor do conjunto: " + menor + " - Soma dos valores do conjunto: " + soma);
		
	}
}
