
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio26{


	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Número de eleitores: ");
		
		int n = scan.nextInt();
		
		int totalVotosCandidatoA = 0;
		int totalVotosCandidatoB = 0;
		int totalVotosCandidatoC = 0;
		
		for(int i = 0; i < n; i++) {
			System.out.println("Em que candidato pretende votar? (A ,B ou C)");
			char candidato = scan.next().toUpperCase().charAt(0);
			if(candidato == 'A') {
				totalVotosCandidatoA ++;
			}
			if(candidato == 'B') {
				totalVotosCandidatoB ++;
			}
			if(candidato == 'C') {
				totalVotosCandidatoC ++;
			}
		}
		
		System.out.println("Nº de votos candidato A: " + totalVotosCandidatoA);
		System.out.println("Nº de votos candidato B: " + totalVotosCandidatoB);
		System.out.println("Nº de votos candidato C: " + totalVotosCandidatoC);
		
		scan.close();
		
	}
}
