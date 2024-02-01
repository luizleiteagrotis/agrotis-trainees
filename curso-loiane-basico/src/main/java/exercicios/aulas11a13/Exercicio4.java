package exercicios.aulas11a13;

import java.util.Scanner;

class Exercicio4{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Digite suas 4 notas bimestrais:");
		
		int nota = 0;
		double resultado = 0;
		
		for(int i = 0; i < 4; i++) {
			nota = scan.nextInt();
			resultado += nota;
		}
		
		resultado = resultado/4;
		
		System.out.println("Média =  " + resultado);
		
	}
}