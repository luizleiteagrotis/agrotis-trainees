
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio11{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Insira dois números: ");
		
		int limite1 = scan.nextInt();
		int limite2 = scan.nextInt();
		
		System.out.println("Números no intervalo:");
		
		int soma = 0;
		
		if(limite1 < limite2) {
			for(int i = limite1; i <= limite2; i++) {
				System.out.println(i);
				soma += i;
			}
			
		} else {
			for(int i = limite2; i <= limite1; i++) {
				System.out.println(i);
				soma += i;
			}
		}
		
		System.out.println("Soma dos números do intervalo:" + soma);
		scan.close();
		
	}
}
