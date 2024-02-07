
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio32{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Fatorial de: ");
		
		int n = scan.nextInt();
		int i = n;
		
		System.out.print( n + "! = ");
		while(i > 0) {
			if(i == 1) {
				System.out.print(i);
			}else {
				System.out.print(i + " . ");
			}
			
			i--;
		}
		
		System.out.print(" = " + fatorial(n));
			
		scan.close();
		
	}

	private static int fatorial(int n) {
		if(n <= 1) {
			return n;
		}
		
		return n*fatorial(n-1);
	}
	
}
