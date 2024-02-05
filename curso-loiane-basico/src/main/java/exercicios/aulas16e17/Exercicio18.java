
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio18{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Insira o tamanho do conjunto:");
		
		int n = scan.nextInt();
		
		int numArray[] = new int[n];
		
		System.out.println("Preencha o conjunto com números:");
		
		
		for(int i = 0; i < numArray.length; i++) {
			
			numArray[i] = scan.nextInt();
			
		}
		
		int maior = 0;
		int menor = Integer.MAX_VALUE;
		int soma = 0;
		
		for(int i = 0; i < numArray.length; i++) {
			
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
