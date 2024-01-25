package ExerciciosAulas11a13;

import java.io.IOException;
import java.util.Scanner;

public class Exercicio11 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		//Faça um programa que peça 2 números inteiros e um número real.
		//Calcule e mostre:
		//a. o produto do dobro do primeiro com metade do segundo;
		//b. a soma do triplo do primeiro com o terceiro;
		//c. o terceiro elevado ao cubo.
		
		System.out.println("Entre com um número inteiro: ");
		int numInteiro1 = scan.nextInt();
		
		System.out.println("Entre com um outro número inteiro: ");
		int numInteiro2 =  scan.nextInt();
		
		System.out.println("Entre com um outro número real: ");
		double numreal3 = scan.nextDouble();
		
		int resultadoInt1 = (numInteiro1 * 2) * (numInteiro2 / 2);
		double resultadoInt2 = (numInteiro1 * 3) * (+ numreal3);
		double resultadoReal3 = (numreal3 * 3);
		
		System.out.println("a. " + resultadoInt1);
		System.out.println("b. " + resultadoInt2);
		System.out.println("c. " + resultadoReal3);
		
	}

}
