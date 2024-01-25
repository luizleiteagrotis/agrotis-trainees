package ExerciciosAulas11a13;

import java.util.Scanner;
import java.util.Locale;

public class Exercicio9 {

	public static void main(String[] args) {
	//Faça um programa que peça a temperatura em graus Farenheit,
	//transforme e mostre a temperatura em graus Celsius
		Scanner scan = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		System.out.println("Entre com a temperatura em graus Farenheit: ");
		int tempF = scan.nextInt();
		
		int tempC = 5 * (tempF - 32) / 9;
		
		System.out.printf(tempF + "ºF é igual a: " + tempC + "ºC");
	}

}
