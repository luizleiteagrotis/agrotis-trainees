package ExerciciosAulas11a13;

import java.util.Scanner;
import java.util.Locale;

public class Exercicio12 {

	public static void main(String[] args) {
	//Faça um programa que peça a temperatura em graus Celsius,
	//transforme e mostre a temperatura em graus Farenheit.
		Scanner scan = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		System.out.println("Digite a sua altura: ");
		double altura = scan.nextDouble();
		
		double ideal = 72.7 * altura - 58;
		
		System.out.printf("Seu peso ideal é: " + "%.0f%n", ideal);
	
	}

}
