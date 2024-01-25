package ExerciciosAulas11a13;

import java.util.Scanner;
import java.util.Locale;

public class Exercicio5 {

	public static void main(String[] args) {
	//Faça um programa que converta metros para centímetros.
		Scanner scan = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		System.out.println("Digite a metragem: ");
		double metros = scan.nextDouble();
		double centímetros = (metros * 100);
		System.out.printf(metros + " metros é igual a: " + centímetros + " centímetros");
	}

}
