package ExerciciosAula15IfElse;

import java.util.Scanner;

public class Ex01 {

	public static void main(String[] args) {
			
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Entre com o primeiro número: ");
		int numero1 = scan.nextInt();
		
		System.out.println("Entre com o segundo número: ");
		int numero2 = scan.nextInt();
		
		if (numero1 > numero2) {
			System.out.println(numero1);
		} else {
			System.out.println(numero2);
		}
		
		}
}