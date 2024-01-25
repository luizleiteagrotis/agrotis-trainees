package ExerciciosAula15IfElse;

import java.util.Scanner;

public class Ex7 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Entre com o numero 1: ");
		int n1 = scan.nextInt();
		System.out.println("Entre com o numero 2: ");
		int n2 = scan.nextInt();
		System.out.println("Entre com o numero 3: ");
		int n3 = scan.nextInt();
		
		if (n1 >= n2 && n1 >= n3){
			System.out.println("n1 é o maior:" + n1);
		} else if (n2 >= n1 && n2 >= n3){
			System.out.println("n2 é o maior:" + n2);
		} else if (n3 >= n1 && n3 >= n2){
			System.out.println("n3 é o maior:" + n3);
		}
		if (n1 <= n2 && n1 <= n3){
			System.out.println("n1 é o menor:" + n1);
		} else if (n2 <= n1 && n2 <= n3){
			System.out.println("n2 é o menor:" + n2);
		} else if (n3 <= n1 && n3 <= n2){
			System.out.println("n3 é o menor:" + n3);	
		}
	}
}