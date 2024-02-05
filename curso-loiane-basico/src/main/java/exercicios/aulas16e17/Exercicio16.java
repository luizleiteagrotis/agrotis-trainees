
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio16{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		int i = 0;
		
		while(true) {
			
			System.out.println("F(" + i + ") = " +fibonacci(i));
			
			if(fibonacci(i) > 500) {
				break;
			}
			
			i++;
			
			
		}
			
		scan.close();
		
	}

	private static int fibonacci(int n) {
		if(n <= 1) {
			return n;
		}
		
		return fibonacci(n-1) + fibonacci(n-2);
	}
	
}
