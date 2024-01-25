package ExerciciosAula15IfElse;

import java.util.Scanner;

public class Ex8 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Preço primeiro produto: ");
		double p1 = scan.nextDouble();
		System.out.println("Preço segundo produto: ");
		double p2 = scan.nextDouble();
		System.out.println("Preço terceiro produto: ");
		double p3 = scan.nextDouble();
		
		if (p1 <= p2 && p1 <= p3){
			System.out.println("Compre o primeiro produto");
		} else if (p2 <= p1 && p2 <= p3){
			System.out.println("Compre o segundo produto");
		} else if (p3 <= p1 && p3 <= p2){		
			System.out.println("Não compre, muito caro.");
		}
	}
}