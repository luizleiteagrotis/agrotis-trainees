package ExerciciosAulas11a13;

import java.util.Scanner;
import java.util.Locale;

public class Exercicio13 {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		System.out.println("Digite a sua altura: ");
		double altura = scan.nextDouble();
		
		System.out.println("Digite a seu peso: ");
		double pesoAtual = scan.nextDouble();

		System.out.println("Digite seu sexo (m/f): ");
		String sexo = scan.next();
		
		double idealf = 62.1 * altura - 44.7;
		double idealm = 72.7 * altura - 58;
		

		if (sexo.equalsIgnoreCase("M")){
			System.out.printf("Seu peso ideal é: " + "%.0f%n", idealm);
		} else if (sexo.equalsIgnoreCase("F")){
			System.out.printf("Seu peso ideal é: " + "%.0f%n", idealf);
		} else {
			System.out.printf("Sexo invalido!");
		} 
			if (pesoAtual < idealf){
				System.out.printf("Você está abaixo do peso ideal");
			} else if (pesoAtual < idealm) {
				System.out.printf("Você está abaixo do peso ideal");
			} else if (pesoAtual > idealf) {
				System.out.printf("Você está acima do peso ideal");
			} else if (pesoAtual > idealm) {
				System.out.printf("Você está acima do peso ideal");
			}			
	}
}