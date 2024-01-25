package ExerciciosAulas11a13;

import java.util.Scanner;
import java.util.Locale;

public class Exercicio14 {

	public static void main(String[] args) {
		
	Scanner scan = new Scanner(System.in);
	
	int limite = 50;
	int excesso = 0;
	double multaExcesso = 4.00;
	
	System.out.println("Entre com o peso total dos peixes: ");
	int peso = scan.nextInt();
	
	if (peso > 50) {
		excesso = peso - 50;
		multaExcesso = excesso * multaExcesso;
		System.out.println("O valor da sua multa será: " + multaExcesso);
	} else if (excesso <= 50){
		multaExcesso = 0;
		System.out.println("O valor da sua multa será: " + multaExcesso);
	}
	}
}