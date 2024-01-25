package ExerciciosAulas11a13;

import java.util.Scanner;
import java.util.Locale;

public class Exercicio4 {

	public static void main(String[] args) {
	//Faça um programa que peça as 4 notas bimestrais e mostre a média.
		Scanner scan = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		System.out.println("Entre com a nota do 1º bimestre:");
		double nota1 = scan.nextDouble();
		System.out.println("Entre com a nota do 2º bimestre:");
		double nota2 = scan.nextDouble();
		System.out.println("Entre com a nota do 3º bimestre:");
		double nota3 = scan.nextDouble();
		System.out.println("Entre com a nota do 4º bimestre:");
		double nota4 = scan.nextDouble();
		double somaNotas = (nota1 + nota2 + nota3 + nota4);
		System.out.println("A média anual é: " + somaNotas/4);
	}

}
