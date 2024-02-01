package exercicios.aulas11a13;

import java.util.Scanner;

class Exercicio10 {
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Temperatura em Celsius:");
		
		double tempCelsius = scan.nextDouble();
		
		double tempFahrenheit  = (tempCelsius * 9)/5 + 32;
		
		System.out.println("Temperatura = " + String.format("%.2f", tempFahrenheit) + "°F");
		
	}
}