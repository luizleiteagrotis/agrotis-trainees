
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio42{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		boolean read = true;
		
		int num = 0;
		
		int firstInterval = 0;
		int secondInterval = 0;
		int thirdInterval = 0;
		int finalInterval = 0;
		
		do {
			System.out.print("Insira um número: ");
			num = scan.nextInt();
			
			if(num < 0) { // para não contar o número negativo no intervalo
				break;
			}
			
			if(num >= 0 && num <= 25) {
				firstInterval++;
			}else if(num <= 50) {
				secondInterval++;
			} else if(num <= 75) {
				thirdInterval++;
			} else if(num <= 100) {
				finalInterval++;
			}
			
		} while(num >= 0);
	
		System.out.println("Números entre [0-25]: " + firstInterval);
		System.out.println("Números entre [26-50]: " + secondInterval);
		System.out.println("Números entre [51-75]: " + thirdInterval);
		System.out.println("Números entre [76-100]: " + finalInterval);
		
		scan.close();
		
	}
}
