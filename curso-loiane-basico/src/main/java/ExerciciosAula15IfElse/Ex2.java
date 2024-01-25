package ExerciciosAula15IfElse;

import java.util.Scanner;

public class Ex2 {

	public static void main(String[] args) {
			
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Entre com o valor: ");
		int valor = scan.nextInt();
		
		if (valor < 0) {
			System.out.println("Negativo");
		} else if (valor >= 0){
			System.out.println("Positivo");
		}	
	}
}