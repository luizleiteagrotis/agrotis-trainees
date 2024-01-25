package ExerciciosAulas11a13;

import java.util.Scanner;

public class Exercicio3 {

	public static void main(String[] args) {
	//Faça um programa que peça dois números e imprima a soma.
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Entre com o primeiro número:");
		int numeroInformado1 = scan.nextInt();
		System.out.print("Entre com o segundo número:");
		int numeroInformado2 = scan.nextInt();
		int resultado = numeroInformado1 + numeroInformado2;
		System.out.print("A soma dos dois números é: " + resultado);
	}

}
