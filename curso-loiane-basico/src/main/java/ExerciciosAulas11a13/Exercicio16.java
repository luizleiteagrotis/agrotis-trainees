package ExerciciosAulas11a13;

import java.util.Scanner;
import java.util.Locale;

public class Exercicio16 {

	public static void main(String[] args) {
	
		Scanner scan = new Scanner(System.in);
		
		double area, b, litros, cobertura, precoLata, latas, total;

		System.out.printf("Entre com o valor em metros quadrados a ser pintado : ");
		area = scan.nextDouble();
		precoLata = 80;
		litros = (area / 3);
		cobertura = 18;
		
		if (area % 54 == 0){
			latas = litros / cobertura;
		} else {
			latas = (int) (litros / cobertura) + 1;
			total = (latas * precoLata);
			System.out.printf(" Voce precisa de %.1f latas e custará %.2f reais ", latas, total);
		}
	}
}