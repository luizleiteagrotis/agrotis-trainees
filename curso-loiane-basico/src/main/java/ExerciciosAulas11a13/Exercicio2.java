package ExerciciosAulas11a13;

import java.util.Scanner;

public class Exercicio2 {

	public static void main(String[] args) {
	//Faça um programa que peça um número e então mostre a mensagem;
	//O número informado foi [número].
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("Entre com um número inteiro: ");
		int numeroInformado = scan.nextInt();
		
		System.out.print("O número informado foi: " + numeroInformado);

	}

}
