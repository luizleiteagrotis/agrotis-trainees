package ExerciciosAulas11a13;

import java.util.Scanner;
import java.util.Locale;

public class Exercicio7 {

	public static void main(String[] args) {
	//Faça um programa que calcule a área de um quadrado,
	//em seguida mostre o dobro desta área para o usuário 
		Scanner scan = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		
		System.out.println("Digite a medida do lado do quadrado: ");
		double l = scan.nextDouble();
		double area = (l * l);
		//ou double area = Math.pow (lado, 2);
		System.out.printf("Área do quadrado = " + "%.1f%n", area);
		System.out.printf("Resultado exercício (Dobro da área do quadrado) = " + "%.1f%n", area * 2);
	}

}
