package ExerciciosAulas11a13;

import java.util.Scanner;
import java.util.Locale;

public class Exercicio10 {

	public static void main(String[] args) {
	//Faça um programa que peça a temperatura em graus Celsius,
	//transforme e mostre a temperatura em graus Farenheit.
		Scanner scan = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		System.out.println("Entre com a temperatura em graus Celsius: ");
		int tempC = scan.nextInt();
		
		int tempF = (tempC * 9 / 5) + 32;
		
		System.out.printf(tempC + "ºC é igual a: " + tempF + "ºF");
	}

}
