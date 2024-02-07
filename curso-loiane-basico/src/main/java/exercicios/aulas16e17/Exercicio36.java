
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio36{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Montar a tabuada de: ");
		
		int num = scan.nextInt();
		
		int limiteInferior = 0;
		int limiteSuperior = 0;
		
		do {
		
			System.out.print("Começar por: ");
		
			limiteInferior = scan.nextInt();
		
			System.out.print("Terminar em: ");
		
			limiteSuperior = scan.nextInt();
		
			if(limiteInferior >= limiteSuperior) {
				System.out.println("Valor inválido! Tente novamente: ");
			}
			
		}while(limiteInferior >= limiteSuperior);
		
		System.out.println("Tabuada de " + num + " começando em " + limiteInferior + " e terminando em " + limiteSuperior + ":");
		
		for(int i = limiteInferior; i <= limiteSuperior; i++) {
			
			System.out.println(num + " X " + i + " = " + num*i);
			
		}
		
		scan.close();
	}
}
