
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio27{


	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Número de turmas: ");
		
		int n = scan.nextInt();
		
		int totalAlunos = 0;
		
		for(int i = 0; i < n; i++) {
			
			System.out.println("Quantos alunos tem na turma " + i + "?");
			int numAlunos = 0;
			
			do {
				numAlunos = scan.nextInt();
				
				if(numAlunos > 40) {
					System.out.println("Número de alunos inválido! Tente novamente:");
				}
				
			} while(numAlunos > 40);
			
			totalAlunos += numAlunos;
		}
		
		double media = totalAlunos/n;
		
		System.out.println("Média de alunos por turma: " + String.format("%.2f", media));
		
		scan.close();
		
	}
}
