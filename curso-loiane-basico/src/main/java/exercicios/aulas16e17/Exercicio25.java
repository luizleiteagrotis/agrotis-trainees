
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio25{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Quantas pessoas participam da turma? ");
		
		int n = scan.nextInt();
		
		int soma = 0;
		
		for(int i = 0; i < n; i++) {
			System.out.println("Insira a idade do participante " + i + " :");
			soma += scan.nextInt();
		}
		
		double media = soma/n;
		
		if(media < 26 && media > 0) {
			System.out.println("Turma jovem");
		} else if(media < 60 && media > 26) {
			System.out.println("Turma adulta");
		} else {
			System.out.println("Turma idosa");
		}
		
		scan.close();
		
	}
}
