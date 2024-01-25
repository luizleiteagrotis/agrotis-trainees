package ExerciciosAulas11a13;

import java.util.Scanner;
import java.util.Locale;

public class Exercicio18 {

	public static void main(String[] args) {
	//Faça um programa que peça a temperatura em graus Celsius,
	//transforme e mostre a temperatura em graus Farenheit.
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Entre com o tamanho do arquivo: ");
		double arquivoTamanho = scan.nextDouble();
		
		System.out.println("Entre com os mb de internet: ");
		double mbInternet = scan.nextDouble();
		
		double tempoDownload = (arquivoTamanho / mbInternet);
		
		System.out.printf("O tempo para download é de " + tempoDownload + " minutos.");
	}

}
