package ExerciciosAulas11a13;

import java.util.Scanner;
import java.util.Locale;

public class Exercicio6 {

	public static void main(String[] args) {
	//Faça um programa que peça o raio de um círculo, calcule e mostre sua área.
		Scanner scan = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		System.out.println("Digite o raio do círculo: ");
		double raio = scan.nextDouble();
		final double p = 3.14159;
		double area = p * (raio * raio);
		//ou double area = Math.PI * Math.pow (raio, 2); 
		System.out.printf("Área do Círculo = " + "%.2f%n", area);
	}

}
