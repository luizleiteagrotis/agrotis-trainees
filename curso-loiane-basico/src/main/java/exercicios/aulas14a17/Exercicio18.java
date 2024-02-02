
package exercicios.aulas14a17;

import java.util.Scanner;

class Exercicio18{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Digite um data no formato dd/mm/aaaa:");
		
		String data = scan.next();
		
		if(data.charAt(2) != '/') {
			System.out.println("Data inválida!");
			System.exit(0);
		}
		if(data.charAt(5) != '/') {
			System.out.println("Data inválida!");
			System.exit(0);
		}
		
		System.out.println("A data informada é válida");
	}
}