package ExerciciosAulas11a13;

import java.util.Scanner;
import java.util.Locale;

public class Exercicio17 {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		double area, b, litros, galoes = 0, preco = 0, preco2 = 0, latas, total;

		System.out.printf("Entre com o valor em metros quadrados a ser pintado : ");
		area = scan.nextDouble();
		litros = (area / 6);
		latas = (litros / 18);
		
		if (latas % 18 != 0){
			latas += 1;
			preco = latas * 80;
			galoes = litros / 3.6;
		} else if (galoes  % 3.6 != 0){
			galoes += 1;
			preco2 = galoes * 25;
		}
		
		int misturalata = (int)litros / 18;
		int misturagalao = (int)(litros - (misturalata * 18) / 3.6);
		
		if (litros - (misturalata * 18) % 3.6 != 0){
			misturagalao += 1;
			System.out.printf("Apenas latas de 18 litros: " + latas + " preço: %d" + preco);
			System.out.printf("Apenas galões de 3.6 litros: " + galoes + " preço: %d" + preco2);
			System.out.printf("Mistura: %d latas e %d galoes = %.2f" + misturalata, misturagalao + misturalata * 80 + misturagalao * 25);
		}
	}		
}