
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio14{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Digite dez numeros inteiros:");
		
		int numArray[] = new int[10];
		
		int countPares = 0;
		int countImpares = 0;
		
		for(int i = 0; i < numArray.length; i++) {
			numArray[i] = scan.nextInt();
			if(numArray[i]%2 == 0) {
				countPares++;
			}
		}
		
		countImpares = numArray.length - countPares;
		
		System.out.println("Quantidade de números pares: " + countPares + " Quantidade de números ímpares: " + countImpares);
		
		scan.close();
		
	}
}
