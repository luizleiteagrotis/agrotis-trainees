
package exercicios.aulas14e15;

import java.util.Scanner;

class Exercicio4{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Digite uma letra:");
		
		char letra = scan.next().toLowerCase().charAt(0);
		
		if(letra == 'a' || letra == 'e' || letra == 'i' || letra == 'o' || letra == 'u') {
			
			System.out.println("Vogal");
			
		} else {
			
			System.out.println("Consoante");
			
		}
	}
}