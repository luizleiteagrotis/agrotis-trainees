package exercicios.aulas11a13;

import java.util.Scanner;

class Exercicio13 {
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Digite sua altura (em metros):");
		
		double altura = scan.nextDouble();
		
		System.out.println("Digite seu sexo (M ou F):");
		
		char sexo  = scan.next().charAt(0);
		
		double pesoIdeal = 0;
		
		if(sexo == 'M' || sexo == 'm') {
			pesoIdeal = (72.7 * altura) - 58;
		} else if( sexo == 'F' || sexo == 'f') {
			pesoIdeal = (62.1 * altura) - 44.7;
		} else {
			System.out.println("Sexo inserido inválido!");
			System.exit(0);
		}
		
		System.out.println("Digite seu peso:");
		
		double peso = scan.nextDouble();
		
		if(peso > pesoIdeal) {
			System.out.println("Seu peso está acima do ideal!");
		} else if(peso < pesoIdeal) {
			System.out.println("Seu peso está abaixo do ideal!");
		} else {
			System.out.println("Seu peso está ideal!");
		}
	}
}