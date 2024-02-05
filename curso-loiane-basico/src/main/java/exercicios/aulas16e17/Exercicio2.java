
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio2{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		String usuario = "";
		String senha = "";

		do {
			System.out.println("Insira seu usuario:");
		
			usuario = scan.next();
			
			System.out.println("Insira sua senha:");
		
			senha = scan.next();
		
			if(senha.contains(usuario)) {
				System.out.println("Sua senha e usuário não podem ser os mesmos!");
			}
			
		}while(senha.contains(usuario));

	}
}
