package exercicios.aulas11a13;

import java.util.Scanner;

class Exercicio11 {
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Insira 2 números inteiros:");
		
		int num1 = scan.nextInt();
		
		int num2  = scan.nextInt();
		
		System.out.println("Insira 1 número real:");
		
		double num3 = scan.nextDouble();
		
		System.out.println("a) " + (2*num1) * (num2/2));
		System.out.println("b) " + (3*num1 + num3));
		System.out.println("c) " + Math.pow(num3, 3));
	}
}