package exercicios.aulas11a13;

import java.util.Scanner;

class Exercicio9 {
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Temperatura em Fahrenheit:");
		
		double tempFahrenheit = scan.nextDouble();
		
		double tempCelsius = (5 * (tempFahrenheit - 32)/9);
		
		System.out.println("Temperatura = " + String.format("%.2f", tempCelsius) + "°C");
		
	}
}