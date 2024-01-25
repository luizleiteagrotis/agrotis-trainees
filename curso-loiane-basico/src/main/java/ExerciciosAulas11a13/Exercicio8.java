package ExerciciosAulas11a13;

import java.util.Scanner;
import java.util.Locale;

public class Exercicio8 {

	public static void main(String[] args) {
	//Faça um programa que calcule a área de um quadrado,
	//em seguida mostre o dobro desta área para o usuário 
		Scanner scan = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		System.out.println("Quantos você ganha por hora? R$");
		double valorHora = scan.nextDouble();
		
		System.out.println("Quantas horas você trabalhou este mês? ");
		double horasMes = scan.nextDouble();
		
		double salario = (valorHora * horasMes);
		System.out.printf("Você receberá um salário no valor de: R$" + "%.2f%n", salario);
	}

}
